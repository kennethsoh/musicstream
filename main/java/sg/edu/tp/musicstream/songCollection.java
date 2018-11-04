package sg.edu.tp.musicstream;

public class songCollection {

    private song[] songs = new song[4];

    public songCollection()
    {
        prepareSongs();
    }

    public song searchById(String id)
    {
        // temporary song object called song and set to null
        song song = null;

        for (int index = 0; index <songs.length; index++)
        {
            // store each song to the temporary song object.
            song = songs[index];

            // compare each song Id to the Id that we want to find, and if equal return this as result
            if (song.getId().equals(id))
            {
                return song;
            }
        }

        // if song cannot be fond in array, return null
        return song;
    }

    private void prepareSongs() {
        song theWayYouLookTonight = new song("S1001",
                "The Way You Look Tonight",
                "Michael Buble",
                "a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=null",
                4.39,
                "michael_buble_collection");

        song billieJean = new song("S1002",
                "Billie Jean",
                "Michael Jackson",
                "b8651ef9c303be61ad354c7485c6620525f0ae4a?cid=2afe87a64b0042dabf51f37318616965",
                4.88,
                "billie_jean");

        song photograph = new song("S1003",
                "Photograph",
                "Ed Sheeran",
                "097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=2afe87a64b0042dabf51f37318616965",
                4.32,
                "photograph");

        song galwayGirl = new song("S1004",
                "Galway Girl",
                "Ed Sheeran",
                "cec1fc40a0220f20d3b91dd28d8e1141ad5e7e25?cid=2afe87a64b0042dabf51f37318616965",
                2.85,
                "divide_cover");




        //097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=2afe87a64b0042dabf51f37318616965
        //watch?v=nSDgHBxUbVQ

        songs[0] = theWayYouLookTonight;
        songs[1] = billieJean;
        songs[2] = photograph;
        songs[3] = galwayGirl;
    }

    public song getNextSong(String currentSongId)
    {
        // temporary song object called song and set to null
        song song = null;
        // starting from index 0 of the song array to the last one,
        // loop through every song item. Increment the index by one after every loop
        // so that the system knows how to go to the next item until the last one
        for(int index = 0; index < songs.length; index++)
        {
            String tempSongId = songs[index].getId();

            if (tempSongId.equals(currentSongId) && (index < songs.length -1))
            {
                song = songs[index +1];

                break;

            }
        }
        return song;
    }

    public song getPrevSong(String currentSongId)
    {
        song song = null;

        for(int index = 0; index < songs.length; index++)
        {
            String tempSongId = songs[index].getId();

            if (tempSongId.equals(currentSongId) && (index <= songs.length -1))
            {
                song = songs[index -1];

                break;
            }

        }
        return song;
    }
}



// 2afe87a64b0042dabf51f37318616965