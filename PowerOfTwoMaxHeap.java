import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap {

    private final int childrenCount;
    private final List<Integer> heap;

    public PowerOfTwoMaxHeap(int exponent) {
        this.childrenCount = (int) Math.pow(2, exponent);
        this.heap = new ArrayList<>();
    }

    private int getParentIndex(int index) {
        return (index - 1) / childrenCount;
    }

    private List<Integer> getChildrenIndices(int index) {
        List<Integer> children = new ArrayList<>();
        int start = childrenCount * index + 1;
        for (int i = 0; i < childrenCount; i++) {
            int childIndex = start + i;
            if (childIndex < heap.size()) {
                children.add(childIndex);
            }
        }
        return children;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = getParentIndex(index);
            if (heap.get(index) > heap.get(parentIndex)) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            List<Integer> childrenIndices = getChildrenIndices(index);
            if (childrenIndices.isEmpty()) {
                break;
            }

            int maxChildIndex = childrenIndices.get(0);
            for (int childIndex : childrenIndices) {
                if (heap.get(childIndex) > heap.get(maxChildIndex)) {
                    maxChildIndex = childIndex;
                }
            }

            if (heap.get(index) < heap.get(maxChildIndex)) {
                swap(index, maxChildIndex);
                index = maxChildIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int maxValue = heap.get(0);
        int lastValue = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastValue);
            heapifyDown(0);
        }

        return maxValue;
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(1);
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);
        heap.insert(15);

        System.out.println("Max value removed: " + heap.popMax());
        System.out.println("Max value removed: " + heap.popMax());

        PowerOfTwoMaxHeap largeHeap = new PowerOfTwoMaxHeap(3);
        for (int i = 1; i <= 20; i++) {
            largeHeap.insert(i);
        }

        System.out.println("Max value removed from large heap: " + largeHeap.popMax());
    }
}
