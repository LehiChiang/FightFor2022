package se.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ZeroCopyTest {

    private static void transferTo() {
        try {
            FileChannel readChannel = FileChannel.open(Paths.get("oldFile.txt"), StandardOpenOption.READ);
            FileChannel writeChannel = FileChannel.open(Paths.get("newFile2.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            readChannel.transferTo(readChannel.position(), readChannel.size(), writeChannel);
            readChannel.close();
            writeChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mmapWrite() {
        try {
            FileChannel readChannel = FileChannel.open(Paths.get("oldFile.txt"), StandardOpenOption.READ);
            MappedByteBuffer data = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, readChannel.size());
            FileChannel writeChannel = FileChannel.open(Paths.get("newFile.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            writeChannel.write(data);
            readChannel.close();
            writeChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
