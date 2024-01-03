package common.binarysearch;

class NBinarySearchTest {

    @org.junit.jupiter.api.Test
    void search() {
        NBinarySearch service = new NBinarySearch();
        int index = service.search(new int[]{2, 4, 7, 9, 11, 15}, 4);
        assert index == 1;
        index = service.search(new int[]{2, 4, 7, 9, 11, 15}, 8);
        assert index == -1;
        index = service.search(new int[]{2, 4, 7, 9, 11, 15}, 7);
        assert index == 2;
    }
}