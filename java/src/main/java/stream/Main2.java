package stream;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        List<Song> list = Utils.buildSongList();

    }

    private static void testForJoining(List<Song> list) {
        // 将歌曲名一行一行的拼接成字符串
        String songNameString = list.stream()
                                    .map(Song::getSongName).
                                            collect(Collectors.joining("\n"));
        System.out.println(songNameString);
    }

    private static void testForPartitionGrouping(List<Song> list) {
        // 将音乐分为老歌新歌两部分，2000年之前为老歌
        Map<Boolean, List<Song>> partitionByYear = list.stream()
                                                       .collect(Collectors.partitioningBy(song -> song.getYear() >= 2000));

        // 将音乐按照艺术家分组
        Map<String, List<Song>> groupByArtist = list.stream()
                                                    .collect(Collectors.groupingBy(song -> song.getArtist()));


        for (Map.Entry<Boolean, List<Song>> entry : partitionByYear.entrySet()) {
            System.out.println(entry.getKey() == true ? "新歌" : "老歌");
            for (Song song : entry.getValue()) {
                System.out.println("    " + song);
            }
        }

        for (Map.Entry<String, List<Song>> entry : groupByArtist.entrySet()) {
            System.out.println(entry.getKey());
            for (Song song : entry.getValue()) {
                System.out.println("    " + song);
            }
        }
    }

    private static void testForToSet(List<Song> list) {
        // 获取所有歌手
        Set<String> artistList = list.stream()
                                     .map(Song::getArtist)
                                     .collect(Collectors.toSet());
        System.out.println(artistList);
    }
}
