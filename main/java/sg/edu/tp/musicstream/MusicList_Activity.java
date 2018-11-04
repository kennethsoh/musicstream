package sg.edu.tp.musicstream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import sg.edu.tp.musicstream.util.AppUtil;
import android.content.Intent;



/**
 *
 */
public class MusicList_Activity extends AppCompatActivity {

    private songCollection songCollection = new songCollection();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list_);


    }


    public void handlerSelection(View view) {
        // Get ID of selected song
        String resourceId = AppUtil.getResourceId(this, view);

       // Search for the selected song based on the ID so
        //that all information/data of the song can be retrieved from a song list.
        song selectedSong = songCollection.searchById(resourceId);

        //Popup a message on the screen to show the title of the song.
        AppUtil.popMessage(this, "Streaming song: " + selectedSong.getTitle());


        //Send the song data to the player screen to be played.
        sendDataToActivity(selectedSong);
    }

    public void sendDataToActivity(song song)
    {
        Intent intent = new Intent(this, PlaySongActivity.class);

        intent.putExtra("id", song.getId());
        intent.putExtra("title", song.getTitle());
        intent.putExtra("artist", song.getArtist());
        intent.putExtra("fileLink", song.getFileLink());
        intent.putExtra("coverArt", song.getCoverArt());

        startActivity(intent);
    }
}

