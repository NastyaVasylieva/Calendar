package com.nastya.calendar.Styles;

public class LessonIcons {
    private String address = "address";
    private String arabic = "arabic";
    private String algebra = "algebra";
    private String art = "art";
    private String art_culture = "art_culture";
    private String astronomy = "astronomy";
    private String biology = "biology";
    private String bus = "bus";
    private String camera = "camera";
    private String chemistry = "chemistry";
    private String drawing = "drawing";
    private String defence = "defence";
    private String ecology = "ecology";
    private String economics = "economics ";
    private String english = "english";
    private String gallery = "gallery";
    private String geography = "geography";
    private String geometry = "geometry";
    private String literature = "literature";
    private String history = "history";
    private String it = "it";
    private String labor_training = "labor_training";
    private String language = "language";
    private String law = "law";
    private String lesson = "lesson";
    private String maths = "maths";
    private String medicine = "medicine";
    private String microphone = "microphone";
    private String physics = "physics";
    private String psychology = "psychology";
    private String russian = "russian";
    private String sport = "sport";
    private String ukrainian = "ukrainian";
    private String turkish = "turkish";
    private String spanish = "spanish";
    private String empty = "empty";
    private String polish = "polish";
    private String slovak = "slovak";
    private String portuguese = "portuguese";
    private String italian = "italian";
    private String greek = "greek";
    private String indonesian = "indonesian";
    private String german = "german";
    private String french = "french";
    private String dutch = "dutch";
    private String search = "search";
    private String calendar = "calendar";
    private String music = "music";
    private String notes = "notes";
    private String government = "government";
    private String books = "books";
    private String notification = "notification";
    private String persian = "persian";
    private String magnet = "magnet";
    private String rocket = "rocket";
    private String time = "time";
    private String bulgarian = "bulgarian";


  /*  public LessonIcons(Resources resources){
        address = resources.getString(R.string.address).toLowerCase();
        arabic = resources.getString(R.string.arabic).toLowerCase();
        algebra = resources.getString(R.string.algebra).toLowerCase();
        art = resources.getString(R.string.art).toLowerCase();
        art_culture = resources.getString(R.string.artculture).toLowerCase();
        astronomy = resources.getString(R.string.astronomy).toLowerCase();
        biology = resources.getString(R.string.biology).toLowerCase();
        chemistry = resources.getString(R.string.chemistry).toLowerCase();
        drawing = resources.getString(R.string.drawing).toLowerCase();
        defence = resources.getString(R.string.defence).toLowerCase();
        ecology = resources.getString(R.string.ecology).toLowerCase();
        economics = resources.getString(R.string.economy).toLowerCase();
        english = resources.getString(R.string.english).toLowerCase();
        geography = resources.getString(R.string.geography).toLowerCase();
        geometry = resources.getString(R.string.geometry).toLowerCase();
        literature = resources.getString(R.string.literature).toLowerCase();
        history = resources.getString(R.string.history).toLowerCase();
        it = resources.getString(R.string.it).toLowerCase();
        labor_training = resources.getString(R.string.work).toLowerCase();
        language = resources.getString(R.string.language).toLowerCase();
        law = resources.getString(R.string.law).toLowerCase();
        lesson = resources.getString(R.string.lesson).toLowerCase();
        maths = resources.getString(R.string.math).toLowerCase();
        medicine = resources.getString(R.string.medicine).toLowerCase();
        physics = resources.getString(R.string.physics).toLowerCase();
        psychology = resources.getString(R.string.psychology).toLowerCase();
        russian = resources.getString(R.string.russian).toLowerCase();
        sport = resources.getString(R.string.sport).toLowerCase();
        ukrainian = resources.getString(R.string.ukrainian).toLowerCase();
        turkish = resources.getString(R.string.turkish).toLowerCase();
        spanish = resources.getString(R.string.spanish).toLowerCase();
        polish = resources.getString(R.string.polish).toLowerCase();
        slovak = resources.getString(R.string.slovak).toLowerCase();
        portuguese = resources.getString(R.string.portuguese).toLowerCase();
        italian = resources.getString(R.string.italian).toLowerCase();
        greek = resources.getString(R.string.greek).toLowerCase();
        indonesian= resources.getString(R.string.indonesian).toLowerCase();
        german = resources.getString(R.string.german).toLowerCase();
        french = resources.getString(R.string.french).toLowerCase();
        dutch = resources.getString(R.string.dutch).toLowerCase();
        greek = resources.getString(R.string.greek).toLowerCase();
        indonesian = resources.getString(R.string.indonesian).toLowerCase();
        search = resources.getString(R.string.search).toLowerCase();
        music = resources.getString(R.string.music).toLowerCase();
        notes = resources.getString(R.string.notes).toLowerCase();
        books = resources.getString(R.string.title_activity_books).toLowerCase();
        notification = resources.getString(R.string.title_activity_notifications).toLowerCase();
        persian = resources.getString(R.string.persian).toLowerCase();
        bulgarian = resources.getString(R.string.bulgarian).toLowerCase();
    }*/

    public String[] getLessonsArray() {
        return new String[]{address, algebra, art, art_culture, astronomy, bus, books, camera,
                calendar, defence, biology, chemistry, drawing, ecology, economics, gallery,
                geography, government, geometry, literature, history, it, labor_training,
                language, law, lesson, maths, magnet, medicine, microphone, music, notes,
                notification, physics, psychology, rocket, sport, search, time, english,
                ukrainian, russian, turkish, spanish, polish, arabic, slovak, persian,
                greek, indonesian, portuguese, italian, german, french, dutch, bulgarian,
                empty};
    }

    /*public int getIcon(String iconName){
        iconName = iconName.toLowerCase();
        if (iconName.equals(algebra))
            return R.drawable.algebra;
        else if (iconName.equals(art))
            return R.drawable.art;
        else if (iconName.equals(art_culture))
            return R.drawable.art_culture;
        else if (iconName.equals(astronomy))
            return R.drawable.astronomy;
        else if (iconName.equals(defence))
            return R.drawable.defence;
        else if (iconName.equals(biology))
            return R.drawable.biology;
        else if (iconName.equals(chemistry))
            return R.drawable.chemistry;
        else if (iconName.equals(drawing))
            return R.drawable.drawing;
        else if (iconName.equals(ecology))
            return R.drawable.ecology;
        else if (iconName.equals(economics))
            return R.drawable.economics;
        else if (iconName.equals(english))
            return R.drawable.eng;
        else if (iconName.equals(geography))
            return R.drawable.geography;
        else if (iconName.equals(geometry))
            return R.drawable.geometry;
        else if (iconName.equals(literature))
            return R.drawable.literature;
        else if (iconName.equals(history))
            return R.drawable.history;
        else if (iconName.equals(it))
            return R.drawable.it;
        else if (iconName.equals(labor_training))
            return R.drawable.labor_training;
        else if (iconName.equals(language))
            return R.drawable.language;
        else if (iconName.equals(law))
            return R.drawable.law;
        else if (iconName.equals(lesson))
            return R.drawable.lesson;
        else if (iconName.equals(maths))
            return R.drawable.maths;
        else if (iconName.equals(medicine))
            return R.drawable.medicine;
        else if (iconName.equals(russian))
            return R.drawable.rus;
        else if (iconName.equals(physics))
            return R.drawable.physics;
        else if (iconName.equals(psychology))
            return R.drawable.psychology;
        else if (iconName.equals(sport))
            return R.drawable.sport;
        else if (iconName.equals(ukrainian))
            return R.drawable.ukr;
        else if (iconName.equals(spanish))
            return R.drawable.spanish;
        else if (iconName.equals(turkish))
            return R.drawable.turkish;
        else if (iconName.equals(bus))
            return R.drawable.bus;
        else if (iconName.equals(microphone))
            return R.drawable.microphone;
        else if (iconName.equals(arabic))
            return R.drawable.arabic;
        else if (iconName.equals(polish))
            return R.drawable.polish;
        else if (iconName.equals(slovak))
            return R.drawable.slovak;
        else if (iconName.equals(portuguese))
            return R.drawable.portuguese;
        else if (iconName.equals(italian))
            return R.drawable.italian;
        else if (iconName.equals(greek))
            return R.drawable.greek;
        else if (iconName.equals(indonesian))
            return R.drawable.indonesian;
        else if (iconName.equals(german))
            return R.drawable.german;
        else if (iconName.equals(french))
            return R.drawable.french;
        else if (iconName.equals(dutch))
            return R.drawable.dutch;
        else if (iconName.equals(greek))
            return R.drawable.greek;
        else if (iconName.equals(indonesian))
            return R.drawable.indonesian;
        else if (iconName.equals(address))
            return R.drawable.address;
        else if (iconName.equals(camera))
            return R.drawable.camera;
        else if (iconName.equals(gallery))
            return R.drawable.gallery;
        else if (iconName.equals(search))
            return R.drawable.search;
        else if (iconName.equals(calendar))
            return R.drawable.pick_date;
        else if (iconName.equals(music))
            return R.drawable.music;
        else if (iconName.equals(notes))
            return R.drawable.notes;
        else if (iconName.equals(government))
            return R.drawable.form_of_government;
        else if (iconName.equals(books))
            return R.drawable.books;
        else if (iconName.equals(notification))
            return R.drawable.notifications;
        else if (iconName.equals(rocket))
            return R.drawable.rocket;
        else if (iconName.equals(persian))
            return R.drawable.persian;
        else if (iconName.equals(magnet))
            return R.drawable.policy;
        else if (iconName.equals(time))
            return R.drawable.timelogo;
        else if (iconName.equals(empty))
            return R.drawable.empty;
        else if (iconName.equals(bulgarian))
            return R.drawable.bulgarian;
        else return R.drawable.lesson;
    }
*/
}


