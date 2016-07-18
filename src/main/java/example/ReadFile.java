package example;

import java.io.InputStream;
import java.util.Locale;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

/**
 * 
 * @author Joel Patrick Llosa
 *
 */
public class ReadFile {

	// to run: java accessToken
	public static void main(String[] args) throws Exception {
		DbxRequestConfig config = new DbxRequestConfig("joel.patrick.llosa", Locale.getDefault().toString());
		DbxClientV2 dbxClient = new DbxClientV2(config, args[0]);

		DbxDownloader<FileMetadata> downloader = dbxClient.files().download("/howToSave.txt");
		InputStream is = downloader.getInputStream();
		if (is != null) {
			int data;
			while ((data = is.read()) >= 0) {
				System.out.print((char) data);
			}
			System.out.println("end of file reached");
		} else {
			System.out.println("file not found...");
		}
		is.close();
	}

}
