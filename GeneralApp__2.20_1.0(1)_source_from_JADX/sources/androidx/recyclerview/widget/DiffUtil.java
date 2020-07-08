package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR = new Comparator<Snake>() {
        public int compare(Snake o1, Snake o2) {
            int cmpX = o1.f29x - o2.f29x;
            return cmpX == 0 ? o1.f30y - o2.f30y : cmpX;
        }
    };

    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        public abstract int getNewListSize();

        public abstract int getOldListSize();

        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return null;
        }
    }

    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        public static final int NO_POSITION = -1;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;

        DiffResult(Callback callback, List<Snake> snakes, int[] oldItemStatuses, int[] newItemStatuses, boolean detectMoves) {
            this.mSnakes = snakes;
            this.mOldItemStatuses = oldItemStatuses;
            this.mNewItemStatuses = newItemStatuses;
            Arrays.fill(this.mOldItemStatuses, 0);
            Arrays.fill(this.mNewItemStatuses, 0);
            this.mCallback = callback;
            this.mOldListSize = callback.getOldListSize();
            this.mNewListSize = callback.getNewListSize();
            this.mDetectMoves = detectMoves;
            addRootSnake();
            findMatchingItems();
        }

        private void addRootSnake() {
            Snake firstSnake = this.mSnakes.isEmpty() ? null : (Snake) this.mSnakes.get(0);
            if (firstSnake == null || firstSnake.f29x != 0 || firstSnake.f30y != 0) {
                Snake root = new Snake();
                root.f29x = 0;
                root.f30y = 0;
                root.removal = false;
                root.size = 0;
                root.reverse = false;
                this.mSnakes.add(0, root);
            }
        }

        private void findMatchingItems() {
            int posOld = this.mOldListSize;
            int posNew = this.mNewListSize;
            for (int i = this.mSnakes.size() - 1; i >= 0; i--) {
                Snake snake = (Snake) this.mSnakes.get(i);
                int endX = snake.f29x + snake.size;
                int endY = snake.f30y + snake.size;
                if (this.mDetectMoves) {
                    while (posOld > endX) {
                        findAddition(posOld, posNew, i);
                        posOld--;
                    }
                    while (posNew > endY) {
                        findRemoval(posOld, posNew, i);
                        posNew--;
                    }
                }
                for (int j = 0; j < snake.size; j++) {
                    int oldItemPos = snake.f29x + j;
                    int newItemPos = snake.f30y + j;
                    int changeFlag = this.mCallback.areContentsTheSame(oldItemPos, newItemPos) ? 1 : 2;
                    this.mOldItemStatuses[oldItemPos] = (newItemPos << 5) | changeFlag;
                    this.mNewItemStatuses[newItemPos] = (oldItemPos << 5) | changeFlag;
                }
                posOld = snake.f29x;
                posNew = snake.f30y;
            }
        }

        private void findAddition(int x, int y, int snakeIndex) {
            if (this.mOldItemStatuses[x - 1] == 0) {
                findMatchingItem(x, y, snakeIndex, false);
            }
        }

        private void findRemoval(int x, int y, int snakeIndex) {
            if (this.mNewItemStatuses[y - 1] == 0) {
                findMatchingItem(x, y, snakeIndex, true);
            }
        }

        public int convertOldPositionToNew(int oldListPosition) {
            if (oldListPosition < 0 || oldListPosition >= this.mOldListSize) {
                StringBuilder sb = new StringBuilder();
                sb.append("Index out of bounds - passed position = ");
                sb.append(oldListPosition);
                sb.append(", old list size = ");
                sb.append(this.mOldListSize);
                throw new IndexOutOfBoundsException(sb.toString());
            }
            int status = this.mOldItemStatuses[oldListPosition];
            if ((status & 31) == 0) {
                return -1;
            }
            return status >> 5;
        }

        public int convertNewPositionToOld(int newListPosition) {
            if (newListPosition < 0 || newListPosition >= this.mNewListSize) {
                StringBuilder sb = new StringBuilder();
                sb.append("Index out of bounds - passed position = ");
                sb.append(newListPosition);
                sb.append(", new list size = ");
                sb.append(this.mNewListSize);
                throw new IndexOutOfBoundsException(sb.toString());
            }
            int status = this.mNewItemStatuses[newListPosition];
            if ((status & 31) == 0) {
                return -1;
            }
            return status >> 5;
        }

        private boolean findMatchingItem(int x, int y, int snakeIndex, boolean removal) {
            int curY;
            int curX;
            int myItemPos;
            if (removal) {
                myItemPos = y - 1;
                curX = x;
                curY = y - 1;
            } else {
                myItemPos = x - 1;
                curX = x - 1;
                curY = y;
            }
            for (int i = snakeIndex; i >= 0; i--) {
                Snake snake = (Snake) this.mSnakes.get(i);
                int endX = snake.f29x + snake.size;
                int endY = snake.f30y + snake.size;
                int changeFlag = 8;
                if (removal) {
                    for (int pos = curX - 1; pos >= endX; pos--) {
                        if (this.mCallback.areItemsTheSame(pos, myItemPos)) {
                            if (!this.mCallback.areContentsTheSame(pos, myItemPos)) {
                                changeFlag = 4;
                            }
                            this.mNewItemStatuses[myItemPos] = (pos << 5) | 16;
                            this.mOldItemStatuses[pos] = (myItemPos << 5) | changeFlag;
                            return true;
                        }
                    }
                    continue;
                } else {
                    for (int pos2 = curY - 1; pos2 >= endY; pos2--) {
                        if (this.mCallback.areItemsTheSame(myItemPos, pos2)) {
                            if (!this.mCallback.areContentsTheSame(myItemPos, pos2)) {
                                changeFlag = 4;
                            }
                            this.mOldItemStatuses[x - 1] = (pos2 << 5) | 16;
                            this.mNewItemStatuses[pos2] = ((x - 1) << 5) | changeFlag;
                            return true;
                        }
                    }
                    continue;
                }
                curX = snake.f29x;
                curY = snake.f30y;
            }
            return false;
        }

        public void dispatchUpdatesTo(Adapter adapter) {
            dispatchUpdatesTo((ListUpdateCallback) new AdapterListUpdateCallback(adapter));
        }

        public void dispatchUpdatesTo(ListUpdateCallback updateCallback) {
            BatchingListUpdateCallback batchingCallback;
            int endY;
            int snakeSize;
            ListUpdateCallback listUpdateCallback = updateCallback;
            if (listUpdateCallback instanceof BatchingListUpdateCallback) {
                ListUpdateCallback listUpdateCallback2 = listUpdateCallback;
                batchingCallback = (BatchingListUpdateCallback) listUpdateCallback;
            } else {
                BatchingListUpdateCallback batchingCallback2 = new BatchingListUpdateCallback(listUpdateCallback);
                BatchingListUpdateCallback batchingListUpdateCallback = batchingCallback2;
                batchingCallback = batchingCallback2;
            }
            ArrayList arrayList = new ArrayList();
            int posOld = this.mOldListSize;
            int posOld2 = posOld;
            int posNew = this.mNewListSize;
            for (int snakeIndex = this.mSnakes.size() - 1; snakeIndex >= 0; snakeIndex--) {
                Snake snake = (Snake) this.mSnakes.get(snakeIndex);
                int snakeSize2 = snake.size;
                int endX = snake.f29x + snakeSize2;
                int endY2 = snake.f30y + snakeSize2;
                if (endX < posOld2) {
                    endY = endY2;
                    dispatchRemovals(arrayList, batchingCallback, endX, posOld2 - endX, endX);
                } else {
                    endY = endY2;
                }
                if (endY < posNew) {
                    int i = endX;
                    snakeSize = snakeSize2;
                    dispatchAdditions(arrayList, batchingCallback, endX, posNew - endY, endY);
                } else {
                    snakeSize = snakeSize2;
                }
                for (int i2 = snakeSize - 1; i2 >= 0; i2--) {
                    if ((this.mOldItemStatuses[snake.f29x + i2] & 31) == 2) {
                        batchingCallback.onChanged(snake.f29x + i2, 1, this.mCallback.getChangePayload(snake.f29x + i2, snake.f30y + i2));
                    }
                }
                posOld2 = snake.f29x;
                posNew = snake.f30y;
            }
            batchingCallback.dispatchLastEvent();
        }

        private static PostponedUpdate removePostponedUpdate(List<PostponedUpdate> updates, int pos, boolean removal) {
            for (int i = updates.size() - 1; i >= 0; i--) {
                PostponedUpdate update = (PostponedUpdate) updates.get(i);
                if (update.posInOwnerList == pos && update.removal == removal) {
                    updates.remove(i);
                    for (int j = i; j < updates.size(); j++) {
                        PostponedUpdate postponedUpdate = (PostponedUpdate) updates.get(j);
                        postponedUpdate.currentPos += removal ? 1 : -1;
                    }
                    return update;
                }
            }
            return null;
        }

        private void dispatchAdditions(List<PostponedUpdate> postponedUpdates, ListUpdateCallback updateCallback, int start, int count, int globalIndex) {
            if (!this.mDetectMoves) {
                updateCallback.onInserted(start, count);
                return;
            }
            for (int i = count - 1; i >= 0; i--) {
                int status = this.mNewItemStatuses[globalIndex + i] & 31;
                if (status == 0) {
                    updateCallback.onInserted(start, 1);
                    for (PostponedUpdate update : postponedUpdates) {
                        update.currentPos++;
                    }
                } else if (status == 4 || status == 8) {
                    int pos = this.mNewItemStatuses[globalIndex + i] >> 5;
                    updateCallback.onMoved(removePostponedUpdate(postponedUpdates, pos, true).currentPos, start);
                    if (status == 4) {
                        updateCallback.onChanged(start, 1, this.mCallback.getChangePayload(pos, globalIndex + i));
                    }
                } else if (status == 16) {
                    postponedUpdates.add(new PostponedUpdate(globalIndex + i, start, false));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("unknown flag for pos ");
                    sb.append(globalIndex + i);
                    sb.append(" ");
                    sb.append(Long.toBinaryString((long) status));
                    throw new IllegalStateException(sb.toString());
                }
            }
        }

        private void dispatchRemovals(List<PostponedUpdate> postponedUpdates, ListUpdateCallback updateCallback, int start, int count, int globalIndex) {
            if (!this.mDetectMoves) {
                updateCallback.onRemoved(start, count);
                return;
            }
            for (int i = count - 1; i >= 0; i--) {
                int status = this.mOldItemStatuses[globalIndex + i] & 31;
                if (status == 0) {
                    updateCallback.onRemoved(start + i, 1);
                    for (PostponedUpdate update : postponedUpdates) {
                        update.currentPos--;
                    }
                } else if (status == 4 || status == 8) {
                    int pos = this.mOldItemStatuses[globalIndex + i] >> 5;
                    PostponedUpdate update2 = removePostponedUpdate(postponedUpdates, pos, false);
                    updateCallback.onMoved(start + i, update2.currentPos - 1);
                    if (status == 4) {
                        updateCallback.onChanged(update2.currentPos - 1, 1, this.mCallback.getChangePayload(globalIndex + i, pos));
                    }
                } else if (status == 16) {
                    postponedUpdates.add(new PostponedUpdate(globalIndex + i, start + i, true));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("unknown flag for pos ");
                    sb.append(globalIndex + i);
                    sb.append(" ");
                    sb.append(Long.toBinaryString((long) status));
                    throw new IllegalStateException(sb.toString());
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public List<Snake> getSnakes() {
            return this.mSnakes;
        }
    }

    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(T t, T t2);

        public abstract boolean areItemsTheSame(T t, T t2);

        public Object getChangePayload(T t, T t2) {
            return null;
        }
    }

    private static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int posInOwnerList2, int currentPos2, boolean removal2) {
            this.posInOwnerList = posInOwnerList2;
            this.currentPos = currentPos2;
            this.removal = removal2;
        }
    }

    static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range() {
        }

        public Range(int oldListStart2, int oldListEnd2, int newListStart2, int newListEnd2) {
            this.oldListStart = oldListStart2;
            this.oldListEnd = oldListEnd2;
            this.newListStart = newListStart2;
            this.newListEnd = newListEnd2;
        }
    }

    static class Snake {
        boolean removal;
        boolean reverse;
        int size;

        /* renamed from: x */
        int f29x;

        /* renamed from: y */
        int f30y;

        Snake() {
        }
    }

    private DiffUtil() {
    }

    public static DiffResult calculateDiff(Callback cb) {
        return calculateDiff(cb, true);
    }

    public static DiffResult calculateDiff(Callback cb, boolean detectMoves) {
        int oldSize = cb.getOldListSize();
        int newSize = cb.getNewListSize();
        ArrayList arrayList = new ArrayList();
        List<Range> stack = new ArrayList<>();
        stack.add(new Range(0, oldSize, 0, newSize));
        int max = oldSize + newSize + Math.abs(oldSize - newSize);
        int[] forward = new int[(max * 2)];
        int[] backward = new int[(max * 2)];
        ArrayList arrayList2 = new ArrayList();
        while (!stack.isEmpty()) {
            Range range = (Range) stack.remove(stack.size() - 1);
            Snake snake = diffPartial(cb, range.oldListStart, range.oldListEnd, range.newListStart, range.newListEnd, forward, backward, max);
            if (snake != null) {
                if (snake.size > 0) {
                    arrayList.add(snake);
                }
                snake.f29x += range.oldListStart;
                snake.f30y += range.newListStart;
                Range left = arrayList2.isEmpty() ? new Range() : (Range) arrayList2.remove(arrayList2.size() - 1);
                left.oldListStart = range.oldListStart;
                left.newListStart = range.newListStart;
                if (snake.reverse) {
                    left.oldListEnd = snake.f29x;
                    left.newListEnd = snake.f30y;
                } else if (snake.removal) {
                    left.oldListEnd = snake.f29x - 1;
                    left.newListEnd = snake.f30y;
                } else {
                    left.oldListEnd = snake.f29x;
                    left.newListEnd = snake.f30y - 1;
                }
                stack.add(left);
                Range right = range;
                if (!snake.reverse) {
                    right.oldListStart = snake.f29x + snake.size;
                    right.newListStart = snake.f30y + snake.size;
                } else if (snake.removal) {
                    right.oldListStart = snake.f29x + snake.size + 1;
                    right.newListStart = snake.f30y + snake.size;
                } else {
                    right.oldListStart = snake.f29x + snake.size;
                    right.newListStart = snake.f30y + snake.size + 1;
                }
                stack.add(right);
            } else {
                arrayList2.add(range);
            }
        }
        Collections.sort(arrayList, SNAKE_COMPARATOR);
        ArrayList arrayList3 = arrayList2;
        int[] iArr = backward;
        int[] iArr2 = forward;
        DiffResult diffResult = new DiffResult(cb, arrayList, forward, backward, detectMoves);
        return diffResult;
    }

    private static Snake diffPartial(Callback cb, int startOld, int endOld, int startNew, int endNew, int[] forward, int[] backward, int kOffset) {
        boolean removal;
        int x;
        int oldSize;
        boolean removal2;
        int x2;
        Callback callback = cb;
        int[] iArr = forward;
        int[] iArr2 = backward;
        int oldSize2 = endOld - startOld;
        int newSize = endNew - startNew;
        if (endOld - startOld < 1) {
        } else if (endNew - startNew < 1) {
            int i = oldSize2;
        } else {
            int delta = oldSize2 - newSize;
            int dLimit = ((oldSize2 + newSize) + 1) / 2;
            Arrays.fill(iArr, (kOffset - dLimit) - 1, kOffset + dLimit + 1, 0);
            Arrays.fill(iArr2, ((kOffset - dLimit) - 1) + delta, kOffset + dLimit + 1 + delta, oldSize2);
            boolean checkInFwd = delta % 2 != 0;
            for (int d = 0; d <= dLimit; d++) {
                int k = -d;
                while (k <= d) {
                    if (k == (-d) || (k != d && iArr[(kOffset + k) - 1] < iArr[kOffset + k + 1])) {
                        x2 = iArr[kOffset + k + 1];
                        removal2 = false;
                    } else {
                        x2 = iArr[(kOffset + k) - 1] + 1;
                        removal2 = true;
                    }
                    int y = x2 - k;
                    while (x2 < oldSize2 && y < newSize && callback.areItemsTheSame(startOld + x2, startNew + y)) {
                        x2++;
                        y++;
                    }
                    iArr[kOffset + k] = x2;
                    if (!checkInFwd || k < (delta - d) + 1 || k > (delta + d) - 1 || iArr[kOffset + k] < iArr2[kOffset + k]) {
                        k += 2;
                    } else {
                        Snake outSnake = new Snake();
                        outSnake.f29x = iArr2[kOffset + k];
                        outSnake.f30y = outSnake.f29x - k;
                        outSnake.size = iArr[kOffset + k] - iArr2[kOffset + k];
                        outSnake.removal = removal2;
                        outSnake.reverse = false;
                        return outSnake;
                    }
                }
                int k2 = -d;
                while (k2 <= d) {
                    int backwardK = k2 + delta;
                    if (backwardK == d + delta || (backwardK != (-d) + delta && iArr2[(kOffset + backwardK) - 1] < iArr2[kOffset + backwardK + 1])) {
                        x = iArr2[(kOffset + backwardK) - 1];
                        removal = false;
                    } else {
                        x = iArr2[(kOffset + backwardK) + 1] - 1;
                        removal = true;
                    }
                    int y2 = x - backwardK;
                    while (true) {
                        if (x > 0 && y2 > 0) {
                            oldSize = oldSize2;
                            if (!callback.areItemsTheSame((startOld + x) - 1, (startNew + y2) - 1)) {
                                break;
                            }
                            x--;
                            y2--;
                            oldSize2 = oldSize;
                        } else {
                            oldSize = oldSize2;
                        }
                    }
                    oldSize = oldSize2;
                    iArr2[kOffset + backwardK] = x;
                    if (checkInFwd || k2 + delta < (-d) || k2 + delta > d || iArr[kOffset + backwardK] < iArr2[kOffset + backwardK]) {
                        k2 += 2;
                        oldSize2 = oldSize;
                    } else {
                        Snake outSnake2 = new Snake();
                        outSnake2.f29x = iArr2[kOffset + backwardK];
                        outSnake2.f30y = outSnake2.f29x - backwardK;
                        outSnake2.size = iArr[kOffset + backwardK] - iArr2[kOffset + backwardK];
                        outSnake2.removal = removal;
                        outSnake2.reverse = true;
                        return outSnake2;
                    }
                }
            }
            throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
        }
        return null;
    }
}