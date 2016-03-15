package learning;

import java.lang.ref.SoftReference;

// if large file is read, this would cause big chunk of memory strongly referred by the byteArray as it has class scope.
// But the large file is useless after checksum is calculated.
public class LeakyChecksum
{
	private byte[] byteArray;
    
    public int getFileChecksum(String fileName) 
    {
        int len = 0; //getFileSize(fileName);
        if (byteArray == null || byteArray.length < len)
            byteArray = new byte[len];
        // readFileContents(fileName, byteArray);
        // calculate checksum and return it
        return 0;
    }
}

class CachingChecksum 
{
    private SoftReference<byte[]> bufferRef;
    
    public synchronized int getFileChecksum(String fileName) 
    {
        int len = 0; //getFileSize(fileName);
        byte[] byteArray = bufferRef.get();
        if (byteArray == null || byteArray.length < len) 
        {
            byteArray = new byte[len];
            bufferRef = new SoftReference<byte[]>(byteArray);
        }
        // readFileContents(fileName, byteArray);
        // calculate checksum and return it
        return 0;
    }
}
