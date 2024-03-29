package com.example.dzairgo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.dzairgo.R;
import com.example.dzairgo.fragments.ActualityFragment;
import com.example.dzairgo.fragments.MapFragment;
import com.example.dzairgo.utils.Article;
import com.example.dzairgo.utils.Commentaire;
import com.example.dzairgo.utils.Compte;
import com.example.dzairgo.utils.Contenu;
import com.example.dzairgo.utils.ElementType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    LinearLayout activity_btn;
    LinearLayout carte_btn;
    RelativeLayout backgroundSelectedFragment;
    TextView activityTxt ;
    TextView carteTxt;
    ImageView activityImg;
    ImageView carteImg;
    FragmentManager fragmentManager;
    ConstraintLayout nv ;
    DrawerLayout dl;
    public ArrayList<Drawable> images;
    public int me = 0;
    public ArrayList<Compte> comptes ;
    public ArrayList<Commentaire> commentaires;
    public ArrayList<Article> articles;
    String currentFragment = "actuality";
    CircleImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        avatar = (CircleImageView) findViewById(R.id.avatar);
//        avatar.setImageDrawable(comptes.get(me).getImageUrl());
//        avatar.setImageDrawable(getDrawable(R.drawable.user_fill));
        activity_btn = (LinearLayout) findViewById(R.id.actuality_btn);
        carte_btn = (LinearLayout) findViewById(R.id.carte_btn);
        backgroundSelectedFragment = (RelativeLayout) findViewById(R.id.selected_fragment_bck);
        activityTxt = (TextView) findViewById(R.id.activity_title_nb);
        carteTxt = (TextView) findViewById(R.id.carte_title_nb);
        activityImg = (ImageView) findViewById(R.id.activity_icon_nb);
        carteImg = (ImageView) findViewById(R.id.map_icon_nb);
        nv = (ConstraintLayout) findViewById(R.id.navigation_bar);
        dl = (DrawerLayout) findViewById(R.id.activity_container);
        images = generFest();
        configActivityBtn();
        configCarteBtn();
        launchActualityFragment();
        comptes = genererCompts();
        commentaires = genererCommenetaires();
        articles = generArticles();
    }
    private void configActivityBtn(){
        activity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateReverse(523);
                carteImg.setImageDrawable(view.getResources().getDrawable(R.drawable.epingle , getTheme()));
                carteTxt.setVisibility(View.GONE);
                activityImg.setImageDrawable(view.getResources().getDrawable(R.drawable.newspaper_blanc , getTheme()));
                activityTxt.setVisibility(View.VISIBLE);
                launchActualityFragment();
                currentFragment = "actuality";
            }
        });
    }
    private void configCarteBtn(){
        carte_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate(523);
                carteImg.setImageDrawable(view.getResources().getDrawable(R.drawable.pin_blanc , getTheme()));
                carteTxt.setVisibility(View.VISIBLE);
                activityImg.setImageDrawable(view.getResources().getDrawable(R.drawable.newspaper_black , getTheme()));
                activityTxt.setVisibility(View.GONE);
                launchMapFragment();
                currentFragment = "map";
            }
        });
    }
    private void animate(int num){
        if(! currentFragment.equals("map")){
            Animation animation1 = new TranslateAnimation(0, num,0, 0);
            animation1.setDuration(300);
            animation1.setFillAfter(true);
            backgroundSelectedFragment.startAnimation(animation1);
        }
    }
    private void animateReverse(int num){
        if(! currentFragment.equals("actuality")){
            Animation animation1 = new TranslateAnimation(num, 0,0, 0);
            animation1.setDuration(400);
            animation1.setFillAfter(true);
            backgroundSelectedFragment.startAnimation(animation1);
        }
    }

    private void launchActualityFragment(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragments_container, ActualityFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("actualite")
                .commit();
    }
    private void launchMapFragment(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragments_container, MapFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("actualite")
                .commit();
    }
    ArrayList<Drawable> generFest(){
        ArrayList<Drawable> images = new ArrayList<>();
        images.add(getDrawable(R.drawable.fest1));
        images.add(getDrawable(R.drawable.fest2));
        images.add(getDrawable(R.drawable.fest3));
        return  images;
    }
    ArrayList<Compte> genererCompts(){
        ArrayList<Compte> comptes = new ArrayList<>();
        comptes.add(new Compte("Laouchedi Karim" , this.getDrawable(R.drawable.person)));
        comptes.add(new Compte("Anfel Boucetta", this.getDrawable(R.drawable.women)));
        comptes.add(new Compte("Athmane Mansour-Bahar" ,this.getDrawable(R.drawable.man_2)));
        comptes.add(new Compte("Karina Saidene" , this.getDrawable(R.drawable.women_2)));
        return comptes;
    }
    ArrayList<Commentaire> genererCommenetaires(){
        ArrayList<Commentaire> commentaires = new ArrayList<>();
        commentaires.add(new Commentaire(comptes.get(1) , "30 Mai 2022" , "15:03" , "Lieu très adapté aux enfants, les gens sont gentils et très accueillant, je recommande"));
        commentaires.add(new Commentaire(comptes.get(2) , "5 Juin 2022" , "10:34" , "Endroit très calme et paisible, parfait pour les randonnées en famille"));
        commentaires.add(new Commentaire(comptes.get(3) , "7 Juin 2022" , "16:20" , "J'aime beaucoup, l'endroit est propre est très bien préservé, les guides sont bien instruits sur l'histoire du lieu. La nourriture à l'intérieur n'est pas très bonne par contre"));
        commentaires.add(new Commentaire(comptes.get(0) , "27 Mai 2022" , "18:52" , "Très jolie comme endroit, je le visite à chaque fois avec mes enfants, la vue est splendide depuis ce lieu"));
        return commentaires;
    }
    ArrayList<Article> generArticles(){
        ArrayList<Article> articles= new ArrayList<>();
        Map<ElementType, Object> structure1 = new HashMap<>();

        structure1.put(ElementType.TEXT , "Le palais des raïs, ou bastion 23, est un monument historique et architectural classé. Il est aussi un centre polyvalent d'art et de culture. Ouvert au publique depuis novembre 1994. Il est constitué de trois palais, cinq maisons, un passage ouvert, une coure centrale, un chemin de ronde et une batterie. Ce palais demeure le dernier et unique témoin du prolongement de la Casbah jusqu'à la mer. Il offre aujourd'hui, aux visiteurs, la possibilité de se promener dans un environnement historique et culturel exceptionnel");

        structure1.put(ElementType.IMAGE , this.getDrawable(R.drawable.place_1));
        ArrayList<Commentaire> commentaires1 = new ArrayList<>();
        commentaires1.add(this.commentaires.get(0));
        commentaires1.add(this.commentaires.get(1));
        Contenu contenu1 = new Contenu(structure1 ,commentaires1);
        articles.add(new Article("Palais des raïs (Bastion 23)" , this.getDrawable(R.drawable.place_1) , "27 Mai 2022" , "18:57" , 2 ,contenu1 , "Alger" ));

        Map<ElementType, Object> structure2 = new HashMap<>();

        structure2.put(ElementType.TEXT , "Un parc paysager installé sur une pente abrupte, en plein centre d'Alger, dans un quartier résiduel très calme. Le parc Beyrout est toujours en très bon état, avec ses mêmes éclats d'antan. Le lieu abrite une variété d'Arbres ombragés des allées spacieux et des espaces verts parfaitement entretenus et gardés. Le lieu dispose également d'un aire de jeux pour enfants, et il offre, même de loin, une vue sur la majestueuse baie d'Alger \n");

        structure2.put(ElementType.IMAGE , this.getDrawable(R.drawable.place_2));
        ArrayList<Commentaire> commentaires2 = new ArrayList<>();
        commentaires2.add(this.commentaires.get(2));
        commentaires2.add(this.commentaires.get(3));
        Contenu contenu2 = new Contenu(structure2, commentaires2);
        articles.add(new Article("Parc de Beyrout (Mont riant)" , this.getDrawable(R.drawable.place_2) , "27 Mai 2022" , "19:08" , 2 ,contenu2 , "Alger" ));

        Map<ElementType, Object> structure3 = new HashMap<>();

        structure3.put(ElementType.TEXT , "«Ce musée qui ambitionne d'avoir une vocation historique, ethnologique et d'interprétation scientifique, se situe au pied de la Casbah d'Alger dans les imposantes Voûtes Khireddine, construites en 1814 par Hadj Ali Pacha et qui ont servi d'atelier de réparation de la flotte sous la régence Ottoman avant que les forces coloniales françaises n'y installent de grands fours pour fournir du pain à leurs soldats. Remontant jusqu'aux périodes, préhistorique, antique, médiévale et ottomane, le Musée de la marine, œuvre à restituer la vie de l'Homme depuis ses premiers contacts avec la mer, en focalisant sur la période ottomane de La Régence d'Alger qui a précédé la colonisation française de l'Algérie» Explique Mme Amel Mokrani Boukari, directrice du musée");

        structure3.put(ElementType.IMAGE , this.getDrawable(R.drawable.place_3));
        ArrayList<Commentaire> commentaires3 = new ArrayList<>();
        commentaires3.add(this.commentaires.get(0));
        Contenu contenu3 = new Contenu(structure3, commentaires3);
        articles.add(new Article("Musée nationale publique maritime " , this.getDrawable(R.drawable.place_3) , "27 Mai 2022" , "19:20" , 1 ,contenu3 , "Alger" ));

        Map<ElementType, Object> structure4 = new HashMap<>();

        structure4.put(ElementType.TEXT , "Ce port est un endroit de grand repère dans l'histoire de l'Algérie, marquant le débarquement de l'armée française, un 5 juillet 1830. Il compte quatre hôtels classés, ainsi que plusieurs restaurants implémentés pour se restaurer si besoin. Le parking est disponible pour 200da. On y propose également des petits tours de 45 mins en poney ou en bateau, pour s'offrir un moment agréable et paisible et profiter de la vue magnifique de ce port ");

        structure4.put(ElementType.IMAGE , this.getDrawable(R.drawable.place_4));
        ArrayList<Commentaire> commentaires4 = new ArrayList<>();
        Contenu contenu4 = new Contenu(structure4, commentaires4);
        articles.add(new Article("Port de Sidi Fredj" , this.getDrawable(R.drawable.place_4) , "27 Mai 2022" , "21:50" , 0 ,contenu4 , "Alger" ));

        Map<ElementType, Object> structure5 = new HashMap<>();

        structure5.put(ElementType.TEXT , "L'ancien parc de Galland a été inauguré en 1915 par Charles de Galland, maire d'Alger de 1910 à 1919 et proviseur du lycée de Ben Aknoun, qui fit don à la ville de ce magnifique terrain. Le parc vient tout juste d'être superbement restauré et il est donc encore plus beau depuis 2018. Ses jardins en terrasses entrecoupés d'escaliers, ses volières et son plan d'eau en faisant alors l'un des lieux de promenades les plus appréciés des Algérois et l'un des plus élégants parc de la ville. Un parc très propre et recommendé pour tout amoureux des lieux de paix et de prospérité ");

        structure5.put(ElementType.IMAGE , this.getDrawable(R.drawable.place_5));
        ArrayList<Commentaire> commentaires5 = new ArrayList<>();
        commentaires5.add(this.commentaires.get(0));
        commentaires5.add(this.commentaires.get(1));
        commentaires5.add(this.commentaires.get(2));
        commentaires5.add(this.commentaires.get(3));
        Contenu contenu5 = new Contenu(structure5, commentaires5);
        articles.add(new Article("Parc de Beyrout (Mont riant)" , this.getDrawable(R.drawable.place_5) , "27 Mai 2022" , "22:00" , 4 ,contenu5 , "Alger" ));
        return articles;
    }
}