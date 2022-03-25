package stream;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Song> buildSongList() {
        return new ArrayList<>(){
            {
                add(new Song("Classic", "MKTO", 2013));
                add(new Song("千千万万", "深海鱼子酱", 2021));
                add(new Song("半生雪", "七叔（叶泽浩）", 2020));
                add(new Song("起风了", "周深", 2020));
                add(new Song("踏山河", "七叔（叶泽浩）", 2020));
                add(new Song("Ring Ring Ring", "S.H.E", 2006));
                add(new Song("1845", "Hotbloods", 2015));
                add(new Song("燕无歇", "七叔（叶泽浩）", 2020));
                add(new Song("失恋阵线联盟", "草蜢", 1990));
                add(new Song("彩虹", "周杰伦", 2007));
                add(new Song("后继者", "任然", 2016));
                add(new Song("飞鸟和蝉", "任然", 2020));
                add(new Song("无人之岛", "任然", 2017));
                add(new Song("New Face", "PSY", 2017));
                add(new Song("大城小爱", "王力宏", 2005));
                add(new Song("给我一首歌的时间", "周杰伦", 2008));
                add(new Song("女孩", "韦礼安", 2016));
                add(new Song("Rollin'", "Brave Girls", 2017));
            }
        };
    }
}
