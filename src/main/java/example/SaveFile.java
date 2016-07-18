package example;

import java.io.FileInputStream;
import java.util.Locale;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CommitInfo;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadSessionCursor;
import com.dropbox.core.v2.files.WriteMode;

/**
 * 
 * @author Joel Patrick Llosa
 *
 */
public class SaveFile {

	// to run: java accessToken
	public static void main(String[] args) throws Exception {
		DbxRequestConfig config = new DbxRequestConfig("joel.patrick.llosa", Locale.getDefault().toString());
        DbxClientV2 dbxClient = new DbxClientV2(config, args[0]);
        
        FileInputStream is = new FileInputStream("howToSave.txt");
        int size = is.available();
        String sessionId = dbxClient.files().uploadSessionStart().uploadAndFinish(is)
                .getSessionId();

        UploadSessionCursor cursor = new UploadSessionCursor(sessionId, (long) size);
        
        CommitInfo commitInfo = CommitInfo.newBuilder("/howToSave.txt")
                .withMode(WriteMode.ADD)
                .build();
        FileMetadata metadata = dbxClient.files().uploadSessionFinish(cursor, commitInfo)
                .finish();
        if (metadata == null) {
            System.out.println("file not saved...");
        } else {
        	System.out.println("file saved...");
        }
	}
}
