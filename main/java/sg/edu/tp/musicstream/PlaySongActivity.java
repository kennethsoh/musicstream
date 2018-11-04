package sg.edu.tp.musicstream;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;

import sg.edu.tp.musicstream.util.AppUtil;

public class PlaySongActivity extends AppCompatActivity {



    // website to stream music
    private static final String BASE_URL = "https://p.scdn.co/mp3-preview/";

    //https://p.scdn.co/mp3-preview/
    //https://www.youtube.com/watch?v=nSDgHBxUbVQ

    // variables are song information that will be used
    private String songId = "";
    private String title = "";
    private String artist = "";
    private String fileLink = "";
    private String coverArt = "";
    private String url = "";

    private MediaPlayer player = null;

    // position of song in playback
    private int musicPosition = 0;

    // this button acts as both play and pause button
    private Button btnPlayPause = null;

    private songCollection songCollection = new songCollection();

    private void retrieveData() {
        Bundle songData = this.getIntent().getExtras();

        songId = songData.getString("id");
        title = songData.getString("title");
        artist = songData.getString("artist");
        fileLink = songData.getString("fileLink");
        coverArt = songData.getString("coverArt");

        url = BASE_URL + fileLink;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        btnPlayPause = findViewById(R.id.btnPlayPause);

        retrieveData();

        displaySong(title, artist, coverArt);


    }

    private void displaySong(String title, String artist, String coverArt) {
        // Retrieve song title from UI and set it as title
        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);

        // Retrieve song Artist for UI and set as artist name
        TextView txtArtist = findViewById(R.id.txtArtist);
        txtArtist.setText(artist);

        // get ID of cover art from drawable folder.
        int imageId = AppUtil.getImageIdFromDrawable(this, coverArt);

        // retrieve cover art ImageView from the UI screen
        ImageView ivCoverArt = findViewById(R.id.imgCoverArt);

        // Set the selected cover art image to the ImageView in the screen
        ivCoverArt.setImageResource(imageId);


    }

    private void preparePlayer() {
        player = new MediaPlayer();

        try {
            // audio stream type = music streaming
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);

            // source of the music
            player.setDataSource(url);


            // prepare player for playback
            player.prepare();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

   /* public void onPrepared(){
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                player.start();
            }
        });
    } */

    public void playOrPauseMusic(View view)
    {

        // if player not created, prepare (create) player
        if (player == null)
            preparePlayer();

        // once created, start regardless
        //player.start();

        // if player is not playing
        if (!player.isPlaying())
        {

            if (musicPosition > 0)
            {
                player.seekTo(musicPosition);
            }

            // Start the player
            player.start();

            // Set the text of the play button to "pause"
            btnPlayPause.setText("Pause");

            setTitle("Now Playing " + title + "-" + artist);

        }
        else
        {

            // pause the music
            pauseMusic();
        }
    }

    private void pauseMusic()

    {
        // pause player
        player.pause();

        // get current position of music that is playing
        musicPosition = player.getCurrentPosition();

        btnPlayPause.setText("Play");

        gracefullyStopWhenMusicEnds();

    }

    private void gracefullyStopWhenMusicEnds()
    {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopActivities();
            }
        });

    }
    private void stopActivities()
    {

        if (player != null)
        {
            btnPlayPause.setText("Play");
            musicPosition = 0;
            setTitle(" ");
            player.stop();
            player.release();
            player = null;
        }
    }


    public void playNext(View view) {
        song nextSong = songCollection.getNextSong(songId);

        if (nextSong != null) {
            songId = nextSong.getId();
            title = nextSong.getTitle();
            artist = nextSong.getArtist();
            fileLink = nextSong.getFileLink();
            coverArt = nextSong.getCoverArt();

            // form the full url of the song from http://comt.azurewebsites.net/
            url = BASE_URL + fileLink;

            // display next song info on screen
            displaySong(title, artist, coverArt);

            // call stopActivities() method to stop current playing song
            stopActivities();

            // call playOrPause() method to play the song
            playOrPauseMusic(view);


        }
    }
    public void playPrev(View view) {
        song prevSong = songCollection.getPrevSong(songId);

        if (prevSong != null) {
            songId = prevSong.getId();
            title = prevSong.getTitle();
            artist = prevSong.getArtist();
            fileLink = prevSong.getFileLink();
            coverArt = prevSong.getCoverArt();

            url = BASE_URL + fileLink;

            displaySong(title, artist, coverArt);

            stopActivities();

            playOrPauseMusic(view);
        }

    }

}
