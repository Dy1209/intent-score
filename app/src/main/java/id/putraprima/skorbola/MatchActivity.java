package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MatchActivity extends AppCompatActivity {

    private String homeTeam;
    private String awayTeam;
    private String winner;
    private int homeScore;
    private int awayScore;
    private TextView scoreHome;
    private TextView scoreAway;
    private TextView homeText;
    private TextView awayText;
    private ImageView homeLogo;
    private ImageView awayLogo;
    private Button addHome;
    private Button addAway;
    private Button cekResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        scoreHome = findViewById(R.id.score_home);
        scoreAway = findViewById(R.id.score_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        addHome = findViewById(R.id.btn_add_home);
        addAway = findViewById(R.id.btn_add_away);
        cekResult = findViewById(R.id.btn_result);

        homeScore = 0;
        awayScore = 0;
        scoreHome.setText(String.valueOf(homeScore));
        scoreAway.setText(String.valueOf(awayScore));

        Bundle bundle = getIntent().getExtras();
        homeTeam = bundle.getString("name_home");
        homeText.setText(homeTeam);
        awayTeam = bundle.getString("name_away");
        awayText.setText(awayTeam);
        homeLogo.setImageURI(Uri.parse(bundle.getString("home_image")));
        awayLogo.setImageURI(Uri.parse(bundle.getString("away_image")));

        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 1;
                scoreHome.setText(String.valueOf(homeScore));
            }
        });

        addAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayScore += 1;
                scoreAway.setText(String.valueOf(awayScore));
            }
        });

        cekResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                winner = "Empty";
                if(homeScore > awayScore){
                    winner = homeTeam;
                }
                else if(homeScore == awayScore){
                    winner = "Seri";
                }
                else{
                    winner = awayTeam;
                }

                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                intent.putExtra("winner", winner);
                startActivity(intent);
            }
        });

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }
}
