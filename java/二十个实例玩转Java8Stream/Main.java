package 二十个实例玩转Java8Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Song> list = Utils.buildSongList();
        list.stream().forEach(System.out::println);

    }

    /**
     * 测试reduce
     * @param list
     */
    private static void testForReduce(List<Song> list) {
        // 求所有音乐年份之和
        Integer sumYear = list.stream()
                              .reduce(0, (sum, song) -> sum + song.getYear(), Integer::sum);

        // 或者
        Integer sumYear2 = list.stream()
                               .reduce(0, (sum, year) -> sum + year.getYear(), (num1, num2) -> num1 + num2);

        System.out.println(sumYear);
        System.out.println(sumYear2);
    }

    /**
     * 测试 FlatMap
     */
    private static void testForFlatMap() {
        List<String> numberList = Arrays.asList("1,2,3,4", "5,6,7");
        List<String> newList = numberList.stream()
                                         .flatMap(string -> {
                                             String[] singleStringArray = string.split(",");
                                             return Arrays.stream(singleStringArray);
                                         })
                                         .collect(Collectors.toList());
        System.out.println(numberList);
        System.out.println(newList);
    }

    /**
     * 测试Map
     * @param list
     */
    private static void testForMap(List<Song> list) {
        // 所有歌名前面加上前缀
        List<String> res = list.stream()
                               .map(song -> "[NEW]" + song.getSongName())
                               .collect(Collectors.toList());
        System.out.println(res);
    }

    /**
     * 测试聚合函数
     *
     * @param list
     */
    private static void testMaxMinCount(List<Song> list) {
        // 获取最长的歌曲名字
        String longestSongName = list.stream()
                                     .max(Comparator.comparingInt(song -> song.getSongName().length()))
                                     .map(Song::getSongName)
                                     .get();

        // 获取最老的歌曲
        Optional<Song> oldestSong = list.stream()
                                        .min(Comparator.comparingInt(song -> song.getYear()));

        // 获取2020以后所有的歌曲的数量
        long songNum = list.stream()
                           .filter(song -> song.getYear() >= 2020)
                           .count();

        System.out.println("最长的歌曲名字：" + longestSongName);
        System.out.println("最老的歌曲：" + oldestSong.get().getSongName());
        System.out.println("2020年后的歌曲数量：" + songNum);
    }

    /**
     * 测试 filter
     *
     * @param list
     */
    private static void testForFilter(List<Song> list) {
        List<String> res = list.stream()
                               .filter(song -> song.getYear() >= 2020)
                               .map(Song::getSongName)
                               .collect(Collectors.toList());
        System.out.println(res);
    }

    /**
     * 测试 for-each, find, match
     *
     * @param list
     */
    private static void testForEachFind(List<Song> list) {
        // 匹配第一个
        Optional<Song> findFirst = list.stream()
                                       .filter(song -> song.getArtist().equals("任然"))
                                       .findFirst();
        // 匹配任意（适用于并行流） 不按照列表顺序
        Optional<Song> findAny = list.stream()
                                     .parallel()
                                     .filter(song -> song.getYear() == 2020)
                                     .findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream()
                               .anyMatch(song -> song.getYear() == 2009);
        System.out.println("匹配第一首歌曲：" + findFirst.get());
        System.out.println("匹配任意一首歌曲：" + (findAny.isPresent() ? findAny.get() : "null"));
        System.out.println("是否存在2008年出版的歌曲：" + anyMatch);
    }
}
