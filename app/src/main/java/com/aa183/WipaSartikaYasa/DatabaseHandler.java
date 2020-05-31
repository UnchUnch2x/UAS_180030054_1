package com.aa183.WipaSartikaYasa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_filmku";
    private final static String TABLE_FILM = "t_film";
    private final static String KEY_ID_FILM = "ID_Film";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_TANGGAL = "Tanggal";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_SINOPSIS = "Sinopsis";
    private final static String KEY_RATING = "Rating";
    private final static String KEY_LINK = "Link";
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());
    private Context context;

    public DatabaseHandler(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FILM = "CREATE TABLE " + TABLE_FILM
                + "(" + KEY_ID_FILM + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_TANGGAL + " DATE, " + KEY_GAMBAR + " TEXT, "
                + KEY_SINOPSIS + " TEXT, " + KEY_RATING + " TEXT, "
                + KEY_LINK + " TEXT);";

        db.execSQL(CREATE_TABLE_FILM);
        inisialisasiFilmAwal(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_FILM;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahFilm(Film dataFilm) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudul());
        cv.put(KEY_TANGGAL, sdFormat.format(dataFilm.getTanggal()));
        cv.put(KEY_GAMBAR, dataFilm.getGambar());
        cv.put(KEY_SINOPSIS, dataFilm.getSinopsis());
        cv.put(KEY_RATING, dataFilm.getRating());
        cv.put(KEY_LINK, dataFilm.getLink());

        db.insert(TABLE_FILM, null, cv);
        db.close();
    }

    public void tambahFilm(Film dataFilm, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudul());
        cv.put(KEY_TANGGAL, sdFormat.format(dataFilm.getTanggal()));
        cv.put(KEY_GAMBAR, dataFilm.getGambar());
        cv.put(KEY_SINOPSIS, dataFilm.getSinopsis());
        cv.put(KEY_RATING, dataFilm.getRating());
        cv.put(KEY_LINK, dataFilm.getLink());

        db.insert(TABLE_FILM, null, cv);
    }

    public void editFilm(Film dataFilm) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudul());
        cv.put(KEY_TANGGAL, sdFormat.format(dataFilm.getTanggal()));
        cv.put(KEY_GAMBAR, dataFilm.getGambar());
        cv.put(KEY_SINOPSIS, dataFilm.getSinopsis());
        cv.put(KEY_RATING, dataFilm.getRating());
        cv.put(KEY_LINK, dataFilm.getLink());

        db.update(TABLE_FILM, cv, KEY_ID_FILM + "=?", new String[]{String.valueOf(dataFilm.getIdFilm())});

        db.close();
    }

    public void hapusFilm(int idFilm) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_FILM, KEY_ID_FILM + "=?", new String[]{String.valueOf(idFilm)});
        db.close();
    }

    public ArrayList<Film> getAllFilm() {
        ArrayList<Film> dataFilm = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_FILM;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if (csr.moveToFirst()){
            do {
                Date tempDate = new Date();
                try {
                    tempDate = sdFormat.parse(csr.getString(2));
                } catch (ParseException er) {
                    er.printStackTrace();
                }

                Film tempFilm = new Film(
                        csr.getInt(0),
                        csr.getString(1),
                        tempDate,
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6)
                );

                dataFilm.add(tempFilm);
            } while (csr.moveToNext());
        }

        return dataFilm;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiFilmAwal(SQLiteDatabase db) {
        int idFilm = 0;
        Date tempDate = new Date();

        //----------menambah data Film---------------
        //Data Film ke1
        try {
            tempDate = sdFormat.parse("21/05/2020 03:04");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Film film1 = new Film(
                idFilm,
                "Sonic the Hedgehog",
                tempDate,
                storeImageFile(R.drawable.cover1),
                "Based on the global blockbuster videogame franchise from Sega, SONIC THE HEDGEHOG tells the story\n" +
                        "of the world's speediest hedgehog as he embraces his new home on Earth. In this live-action adventure\n" +
                        "comedy, Sonic and his new best friend Tom (James Marsden) team up to defend the planet from the evil\n" +
                        "genius Dr. Robotnik (Jim Carrey) and his plans for world domination. The family-friendly film also stars\n" +
                        "Tika Sumpter and Ben Schwartz as the voice of Sonic.\n",
                "6.6",
                "https://yts.mx/movies/sonic-the-hedgehog-2020"
        );

        tambahFilm(film1, db);
        idFilm++;

        //Data film ke2
        try {
            tempDate = sdFormat.parse("26/05/2020 20:37");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Film film2 = new Film(
                idFilm,
                "Weathering with You",
                tempDate,
                storeImageFile(R.drawable.cover2),
                "A high-school boy who has run away to Tokyo befriends a girl who appears to be able to manipulate the weather.",
                "7.6",
                "https://yts.mx/movies/weathering-with-you-2019"
        );

        tambahFilm(film2, db);
        idFilm++;

        //Data Film ke3
        try {
            tempDate = sdFormat.parse("27/09/2017 16:38");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Film film3 = new Film(
                idFilm,
                "Despicable Me 3",
                tempDate,
                storeImageFile(R.drawable.cover3),
                "After he is fired from the Anti-Villain League for failing to take down the latest bad guy to threaten\n" +
                        "humanity, Gru (Steve Carell) finds himself in the midst of a major identity crisis. But when a mysterious\n" +
                        "stranger shows up to inform Gru that he has a long-lost twin brother - a brother who desperately wishes\n" +
                        "to follow in his twin's despicable footsteps - one former supervillain will rediscover just how good it feels\n" +
                        "to be bad.\n",
                "6.3",
                "https://yts.mx/movies/despicable-me-3-2017"
        );

        tambahFilm(film3, db);
        idFilm++;

        //Data Film ke4
        try {
            tempDate = sdFormat.parse("18/03/2020 16:39");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Film film4 = new Film(
                idFilm,
                "Frozen 2",
                tempDate,
                storeImageFile(R.drawable.cover4),
                "Having harnessed her ever-growing power after lifting the dreadful curse of the eternal winter in Frozen\n" +
                        "(2013), the beautiful conjurer of snow and ice, Queen Elsa, now rules the peaceful kingdom of\n" +
                        "Arendelle, enjoying a happy life with her sister, Princess Anna. However, a melodious voice that only\n" +
                        "Elsa can hear keeps her awake, inviting her to the mystical enchanted forest that the sisters' father told\n" +
                        "them about a long time ago. Now, unable to block the thrilling call of the secret siren, Elsa, along with\n" +
                        "Anna, Kristoff, Olaf, and Sven summons up the courage to follow the voice into the unknown, intent on\n" +
                        "finding answers in the perpetually misty realm in the woods. More and more, an inexplicable imbalance\n" +
                        "is hurting not only her kingdom but also the neighboring tribe of Northuldra. Can Queen Elsa put her\n" +
                        "legendary magical skills to good use to restore peace and stability?\n",
                "6.9",
                "https://yts.mx/movies/frozen-ii-2019"
        );

        tambahFilm(film4, db);
        idFilm++;

        //Data Film ke5
        try {
            tempDate = sdFormat.parse("19/03/2020 17:34");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Film film5 = new Film(
                idFilm,
                "Toy Story 4",
                tempDate,
                storeImageFile(R.drawable.cover5),
                "Woody, Buzz Lightyear and the rest of the gang embark on a road trip with Bonnie and a new toy named\n" +
                        "Forky. The adventurous journey turns into an unexpected reunion as Woody's slight detour leads him to\n" +
                        "his long-lost friend Bo Peep. As Woody and Bo discuss the old days, they soon start to realize that\n" +
                        "they're two worlds apart when it comes to what they want from life as a toy.\n",
                "7.8",
                "https://yts.mx/movies/toy-story-4-2019"
        );

        tambahFilm(film5, db);
    }
}
