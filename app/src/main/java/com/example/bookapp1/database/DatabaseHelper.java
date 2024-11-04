package com.example.bookapp1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookapp1.models.Author;
import com.example.bookapp1.models.Novel;
import com.example.bookapp1.models.Reader;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DbReadStoryProject_PRM392.db";
    private static final int DATABASE_VERSION = 6;
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;"); // Enable foreign keys
        db.execSQL(createAdminTable());
        db.execSQL(createReaderTable());
        db.execSQL(createAuthorTable());
        db.execSQL(createCategoryTable());
        db.execSQL(createNovelTable());
        db.execSQL(createNovelCategoryTable());
        db.execSQL(createChapterTable());
        db.execSQL(createCommentTable());
        db.execSQL(createCommentRelationshipTable());
        db.execSQL(createBookmarkTable());
        seedData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("PRAGMA foreign_keys=ON;");
        db.beginTransaction();
        try {
            // Drop all tables
            db.execSQL("DROP TABLE IF EXISTS Bookmark");
            db.execSQL("DROP TABLE IF EXISTS Comment_Relationship");
            db.execSQL("DROP TABLE IF EXISTS Comment");
            db.execSQL("DROP TABLE IF EXISTS Chapter");
            db.execSQL("DROP TABLE IF EXISTS Novel_Category");
            db.execSQL("DROP TABLE IF EXISTS Novel");
            db.execSQL("DROP TABLE IF EXISTS Category");
            db.execSQL("DROP TABLE IF EXISTS Author");
            db.execSQL("DROP TABLE IF EXISTS Reader");
            db.execSQL("DROP TABLE IF EXISTS Admin");
            onCreate(db); // Recreate all tables
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        Log.d("DatabaseHelper", "Database upgraded to version " + newVersion);
    }

    // Table creation SQL strings
    private String createAdminTable() {
        return "CREATE TABLE Admin (" +
                "admin_id INTEGER PRIMARY KEY, " +
                "Username TEXT NOT NULL, " +
                "Password TEXT NOT NULL, " +
                "Role TEXT, " +
                "Email TEXT, " +
                "Phone TEXT, " +
                "Confirm_code TEXT, " +
                "Created_date TEXT, " +
                "Updated_date TEXT, " +
                "Last_login TEXT)";
    }

    private String createReaderTable() {
        return "CREATE TABLE Reader (" +
                "reader_id INTEGER PRIMARY KEY, " +
                "Username TEXT NOT NULL, " +
                "Password TEXT NOT NULL, " +
                "Role TEXT, " +
                "Status TEXT, " +
                "Email TEXT, " +
                "Phone TEXT, " +
                "Confirm_code TEXT, " +
                "Year INTEGER, " +
                "Gender TEXT, " +
                "Avatar TEXT, " +
                "Title TEXT, " +
                "Coin INTEGER, " +
                "Created_date TEXT, " +
                "Updated_date TEXT, " +
                "Last_login TEXT)";
    }

    private String createAuthorTable() {
        return "CREATE TABLE Author (" +
                "author_id INTEGER PRIMARY KEY, " +
                "Username TEXT NOT NULL, " +
                "Password TEXT NOT NULL, " +
                "Name TEXT, " +
                "Role TEXT, " +
                "Status TEXT, " +
                "Email TEXT, " +
                "Phone TEXT, " +
                "Confirm_code TEXT, " +
                "Year INTEGER, " +
                "Gender TEXT, " +
                "Avatar TEXT, " +
                "Title TEXT, " +
                "Coin INTEGER, " +
                "Created_date TEXT, " +
                "Updated_date TEXT, " +
                "Last_login TEXT)";
    }

    private String createCategoryTable() {
        return "CREATE TABLE Category (" +
                "category_id INTEGER PRIMARY KEY, " +
                "Name TEXT NOT NULL, " +
                "Description TEXT)";
    }

    private String createNovelTable() {
        return "CREATE TABLE Novel (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "author TEXT, " +
                "category TEXT, " +
                "totalChapters INTEGER, " +
                "thumbnail TEXT, " +
                "summary TEXT, " +
                "content TEXT, " + // Add the new content field here
                "author_id INTEGER, " +
                "FOREIGN KEY (author_id) REFERENCES Author (author_id) ON DELETE CASCADE)";
    }

    private String createNovelCategoryTable() {
        return "CREATE TABLE Novel_Category (" +
                "novel_id INTEGER, " +
                "category_id INTEGER, " +
                "Created_date TEXT, " +
                "Updated_date TEXT, " +
                "PRIMARY KEY (novel_id, category_id), " +
                "FOREIGN KEY (novel_id) REFERENCES Novel (id) ON DELETE CASCADE, " +
                "FOREIGN KEY (category_id) REFERENCES Category (category_id) ON DELETE CASCADE)";
    }

    private String createChapterTable() {
        return "CREATE TABLE Chapter (" +
                "chapter_id INTEGER PRIMARY KEY, " +
                "Title TEXT NOT NULL, " +
                "Length INTEGER, " +
                "Content TEXT, " +
                "Status TEXT, " +
                "isVip INTEGER, " +
                "coinRequired INTEGER, " +
                "Created_date TEXT, " +
                "Updated_date TEXT, " +
                "novel_id INTEGER, " +
                "FOREIGN KEY (novel_id) REFERENCES Novel (id) ON DELETE CASCADE)";
    }

    private String createCommentTable() {
        return "CREATE TABLE Comment (" +
                "comment_id INTEGER PRIMARY KEY, " +
                "Context TEXT NOT NULL, " +
                "Created_date TEXT, " +
                "reader_id INTEGER, " +
                "chapter_id INTEGER, " +
                "FOREIGN KEY (reader_id) REFERENCES Reader (reader_id) ON DELETE CASCADE, " +
                "FOREIGN KEY (chapter_id) REFERENCES Chapter (chapter_id) ON DELETE CASCADE)";
    }

    private String createCommentRelationshipTable() {
        return "CREATE TABLE Comment_Relationship (" +
                "child_comment_id INTEGER, " +
                "parent_comment_id INTEGER, " +
                "PRIMARY KEY (child_comment_id, parent_comment_id), " +
                "FOREIGN KEY (child_comment_id) REFERENCES Comment (comment_id) ON DELETE CASCADE, " +
                "FOREIGN KEY (parent_comment_id) REFERENCES Comment (comment_id) ON DELETE NO ACTION)";
    }

    private String createBookmarkTable() {
        return "CREATE TABLE Bookmark (" +
                "novel_id INTEGER, " +
                "reader_id INTEGER, " +
                "recently_chapter TEXT, " +
                "Created_date TEXT, " +
                "Updated_date TEXT, " +
                "PRIMARY KEY (novel_id, reader_id), " +
                "FOREIGN KEY (novel_id) REFERENCES Novel (id) ON DELETE CASCADE, " +
                "FOREIGN KEY (reader_id) REFERENCES Reader (reader_id) ON DELETE CASCADE)";
    }

    public void seedData(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Reader", null);
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();

        if (rowCount > 0) {
            Log.d("DatabaseSeeder", "Data already exists, skipping seeding.");
            return;
        }

        try {
            ContentValues readerValues = new ContentValues();
            readerValues.put("Username", "readerUser");
            readerValues.put("Password", "readerPass");
            readerValues.put("Role", "Reader");
            readerValues.put("Status", "Active");
            readerValues.put("Email", "reader@example.com");
            readerValues.put("Phone", "987654321");
            readerValues.put("Confirm_code", "def456");
            readerValues.put("Year", 1995);
            readerValues.put("Gender", "Female");
            readerValues.put("Avatar", "avatar_url_reader");
            readerValues.put("Title", "Premium Reader");
            readerValues.put("Coin", 50);
            readerValues.put("Created_date", "2024-01-04");
            readerValues.put("Updated_date", "2024-01-05");
            readerValues.put("Last_login", "2024-01-06");
            db.insert("Reader", null, readerValues);
            Log.d("DatabaseSeeder", "Data seeding successful.");
        } catch (Exception e) {
            Log.e("DatabaseSeeder", "Error while seeding data: " + e.getMessage(), e);
        }
    }

    public long insertAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", author.getUsername());
        values.put("Password", author.getPassword());
        values.put("Role", author.getRole());
        values.put("Status", author.getStatus());
        values.put("Email", author.getEmail());
        values.put("Phone", author.getPhone());
        values.put("Confirm_code", author.getConfirmCode());
        values.put("Year", author.getYear());
        values.put("Gender", author.getGender());
        values.put("Avatar", author.getAvatar());
        values.put("Title", author.getTitle());
        values.put("Coin", author.getCoin());
        return db.insert("Author", null, values);
    }

    public List<Author> getAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Author", null);
        if (cursor.moveToFirst()) {
            do {
                int authorId = cursor.getInt(cursor.getColumnIndexOrThrow("author_id"));
                String username = cursor.getString(cursor.getColumnIndexOrThrow("Username"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
                Author author = new Author(authorId, username, email);
                authorList.add(author);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return authorList;
    }

    public int updateAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", author.getUsername());
        values.put("Email", author.getEmail());
        return db.update("Author", values, "author_id = ?", new String[]{String.valueOf(author.getAuthorId())});
    }

    public int deleteAuthor(int authorId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Author", "author_id = ?", new String[]{String.valueOf(authorId)});
    }

    public long registerReader(Reader reader) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", reader.getUsername());
        values.put("Password", reader.getPassword());
        values.put("Email", reader.getEmail());
        values.put("Phone", reader.getPhone());
        values.put("Status", "Active");
        values.put("Role", "Reader");
        return db.insert("Reader", null, values);
    }

    public Reader loginReader(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Reader WHERE Email = ? AND Password = ?", new String[]{email, password});
        if (cursor.moveToFirst()) {
            int readerId = cursor.getInt(cursor.getColumnIndexOrThrow("reader_id"));
            String username = cursor.getString(cursor.getColumnIndexOrThrow("Username"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
            String status = cursor.getString(cursor.getColumnIndexOrThrow("Status"));
            String role = cursor.getString(cursor.getColumnIndexOrThrow("Role"));
            cursor.close();
            return new Reader(readerId, username, password, email, phone, status, role);
        }
        cursor.close();
        return null;
    }

    public List<Novel> getNovelsByReaderId(int readerId) {
        List<Novel> novelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to select novels bookmarked by the reader
        String query = "SELECT Novel.* FROM Novel " +
                "JOIN Bookmark ON Novel.id = Bookmark.novel_id " +
                "WHERE Bookmark.reader_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(readerId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));
                int totalChapters = cursor.getInt(cursor.getColumnIndexOrThrow("totalChapters"));
                String thumbnail = cursor.getString(cursor.getColumnIndexOrThrow("thumbnail"));
                String summary = cursor.getString(cursor.getColumnIndexOrThrow("summary"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content")); // Get content

                // Create a new Novel object and add it to the list
                Novel novel = new Novel(id, title, author, category, totalChapters, thumbnail, summary, content, readerId);
                novelList.add(novel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return novelList;
    }

    public List<Novel> getAllNovels() {
        List<Novel> novelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM Novel";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));
                int totalChapters = cursor.getInt(cursor.getColumnIndexOrThrow("totalChapters"));
                String thumbnail = cursor.getString(cursor.getColumnIndexOrThrow("thumbnail"));
                String summary = cursor.getString(cursor.getColumnIndexOrThrow("summary"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content")); // Get content

                Novel novel = new Novel(id, title, author, category, totalChapters, thumbnail, summary, content, 0); // 0 for readerId if not needed
                novelList.add(novel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return novelList;
    }

    public Novel getNovelById(int novelId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Novel", null, "id = ?", new String[]{String.valueOf(novelId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Novel novel = new Novel(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("author")),
                    cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("totalChapters")),
                    cursor.getString(cursor.getColumnIndexOrThrow("thumbnail")),
                    cursor.getString(cursor.getColumnIndexOrThrow("summary")),
                    cursor.getString(cursor.getColumnIndexOrThrow("content")), // Get content
                    cursor.getInt(cursor.getColumnIndexOrThrow("author_id"))
            );
            cursor.close();
            return novel;
        } else {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
    }

    public boolean addNovel(Novel novel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", novel.getTitle());
        values.put("author", novel.getAuthor());
        values.put("category", novel.getCategory());
        values.put("totalChapters", novel.getTotalChapters());
        values.put("thumbnail", novel.getThumbnailUri());
        values.put("summary", novel.getSummary());
        values.put("content", novel.getContent()); // Add content
        values.put("author_id", novel.getAuthorId());
        long result = db.insert("Novel", null, values);
        db.close();
        return result != -1;
    }

    public boolean updateNovel(Novel novel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", novel.getTitle());
        values.put("author", novel.getAuthor());
        values.put("category", novel.getCategory());
        values.put("totalChapters", novel.getTotalChapters());
        values.put("thumbnail", novel.getThumbnailUri());
        values.put("summary", novel.getSummary());
        values.put("content", novel.getContent()); // Update content
        values.put("author_id", novel.getAuthorId());

        int result = db.update("Novel", values, "id = ?", new String[]{String.valueOf(novel.getId())});
        db.close();
        return result > 0; // Returns true if at least one row was updated
    }


    public boolean deleteNovel(int novelId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("Novel", "id = ?", new String[]{String.valueOf(novelId)});
        db.close();
        return result > 0;
    }

    public boolean isNovelExistsExcludingId(String title, int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM Novel WHERE title = ? AND id != ?";
        Cursor cursor = db.rawQuery(query, new String[]{title, String.valueOf(id)});
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0; // Return true if a novel with this title exists, excluding the current ID
    }


}
