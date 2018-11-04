package sg.edu.tp.musicstream;

public class song {

    private String Id;
    private String title;
    private String artist;
    private String fileLink;
    private double songLength;
    private String coverArt;


    public song(String Id, String title, String artist, String fileLink, double songLength, String coverArt)
    {
        this.Id = Id;
        this.title = title;
        this.artist = artist;
        this.fileLink = fileLink;
        this.songLength = songLength;
        this.coverArt = coverArt;
    }



    public String getId(){return Id; }
    public void SetId(String Id) {this.Id = Id; }

    public String getTitle() {return title; }
    public void SetTitle(String title) {this.title = title; }

    public String getArtist() {return artist; }
    public void SetArtist(String artist) {this.artist = artist; }

    public String getFileLink() {return fileLink; }
    public void SetFileLink(String fileLink) {this.fileLink = fileLink; }

    public double getSongLength() {return songLength; }
    public void SetSongLength(double songLength) {this.songLength = songLength; }

    public String getCoverArt() {return coverArt; }
    public void SetCoverArt(String coverArt) {this.coverArt = coverArt; }
}
