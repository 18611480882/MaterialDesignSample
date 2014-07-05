package rejasupotaro.sample.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Article implements Parcelable {

    private int id;

    private String photoUrl;

    private String title;

    private String body;

    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Article(int id, String photoUrl, String title, String body, String url) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.body = body;
        this.url = url;
    }

    public static List<Article> createDummyData() {
        List<Article> articleList = new ArrayList<Article>() {{
            add(new Article(0,
                    "http://img.cpcdn.com/cms_articles/1883/m/fd5658b0895479e3b8e7c8000098ee36.jpg?u=1833855&p=1404107326",
                    "暑い日にぴったり！辛くてさっぱり「タコライス」を作ってみよう！",
                    "「タコライス」とは、メキシコ発祥の「タコス」の具をごはんの上にトッピングした、沖縄料理のこと。ピリ辛味のミート＆生野菜がたっぷりのっているので、ガッツリだけどさっぱり食べられる夏におすすめの一品なんです。",
                    "http://cookpad.com/articles/1883"));
            add(new Article(1,
                    "http://img.cpcdn.com/cms_articles/1891/m/e6d6ff2b70e5f9a397f2a0972f2551af.jpg?u=8211561&p=1404197252",
                    "灼熱のスペインで生まれた「ガスパチョ」で夏をハッピーに乗りきろう♪",
                    "暑さで食欲が落ちる季節には太陽の国・スペインで生まれたガスパチョはいかが？飲むサラダとしても知られる野菜たっぷりの冷製スープは、暑い日の疲労回復にも効果抜群です。さっそくチェックしてみましょう！！",
                    "http://cookpad.com/articles/1891"));
            add(new Article(2,
                    "http://img.cpcdn.com/cms_articles/1913/m/1392c97ef6d02ba840f0106d070c75af.jpg?u=7061456&p=1404374982",
                    "熱中症対策のカギは水以上に塩だった！【熱中症対策②】",
                    "この7月1日から熱中症予防強化月間がスタート。梅雨明けの時期にあたる7月は、急激な温度の上昇に体が慣れず、毎年、熱中症で病院に運ばれる人が増えるのだとか。「自分はだいじょうぶ」と思わずに、しっかり対策しておきましょう！　今回は熱中症を予防するための基礎知識をチェックします。",
                    "http://cookpad.com/articles/1913"));
            add(new Article(3,
                    "http://img.cpcdn.com/cms_articles/1898/m/3247b71cdb3a536d4c5f32d24e40f769.jpg?u=9769263&p=1404285567",
                    "もう常識！？この夏は“パン×アイス”から目が離せない！！",
                    "暑い夏、アイスのおいしい季節が到来しました。ところで皆さんはアイスってどう食べてますか？カップ派？コーン派？この夏おススメしたいのは「パン」との組み合わせなんです。今年はパン派で決まり！",
                    "http://cookpad.com/articles/1898"));
            add(new Article(4,
                    "http://img.cpcdn.com/cms_articles/1856/m/b744061c75aa50ae6eac4554637fcda1.jpg?u=1833855&p=1403849207",
                    "「朝ご飯を食べないと太る」って、ほんとなの？管理栄養士に聞いてみた！",
                    "みなさん、朝ごはんはきちんと食べていますか？忙しくてなかなか用意できず、「ダイエットになるからいいかな」なんて思っている方もいらっしゃるかもしれません。実は、それがダイエットが上手くいかない理由かもしれません。",
                    "http://cookpad.com/articles/1856"));
        }};
        return articleList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.photoUrl);
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeString(this.url);
    }

    private Article(Parcel in) {
        this.id = in.readInt();
        this.photoUrl = in.readString();
        this.title = in.readString();
        this.body = in.readString();
        this.url = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
