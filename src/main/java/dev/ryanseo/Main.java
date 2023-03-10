package dev.ryanseo;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class Main {
    private static final String DEVELOPER_KEY = "AIzaSyAA_VUqOVdF9z2gujF84petXl4GuNlo5-E";
    private static final String APPLICATION_NAME = "Playlist Backup";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public static void main(String[] args)
            throws GeneralSecurityException, IOException {

        ArrayList<Video> playlistVideos1 = PlaylistActions.retrievePlaylist("PL6Y2H3WgxO8EaAein4qjh0omQ81AtJJc2", DEVELOPER_KEY, "Ryan's Cultured Music");
        ArrayList<Video> playlistVideos2 = PlaylistActions.retrievePlaylist("PL6Y2H3WgxO8G--X_l6SP0faS6gFR4tJJu", DEVELOPER_KEY, "Side Culture");
        ArrayList<Video> playlistVideos3 = PlaylistActions.retrievePlaylist("PL6Y2H3WgxO8EybvA8KeKUweGL300AfswY", DEVELOPER_KEY, "Sing-a-long");

        printPlaylist(playlistVideos2);
        printPlaylist(playlistVideos3);
        printPlaylist(playlistVideos1); 
        
//        PlaylistActions.saveVideosToJson(playlistVideos);
    }

    public static void printPlaylist(ArrayList<Video> playlistVideos) {
        for (Video v : playlistVideos) {
            if (v.getTitle() != null) {
                System.out.println(v.getTitle() + "  |  PrivacyStatus: " + v.getPrivacyStatus() + "  |  VideoOwner: " + v.getVideoOwnerChannelTitle());
            } else {
                throw new RuntimeException("A misbehaving video is present in playlist");
            }
        }

        System.out.println();
        System.out.println(playlistVideos.size() + " videos");
        System.out.println("Latest video: " + playlistVideos.get(0).getTitle() + " by " + playlistVideos.get(0).getVideoOwnerChannelTitle());
        System.out.println();
        System.out.println("?????????????????????????????????????????????????????????");
    }
}