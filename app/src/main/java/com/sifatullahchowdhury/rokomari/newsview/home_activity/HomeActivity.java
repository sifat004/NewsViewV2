package com.sifatullahchowdhury.rokomari.newsview.home_activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sifatullahchowdhury.rokomari.newsview.R;
import com.sifatullahchowdhury.rokomari.newsview.adapter.ArticleCardRecyclerAdapter;
import com.sifatullahchowdhury.rokomari.newsview.adapter.ArticleListRecyclerAdapter;
import com.sifatullahchowdhury.rokomari.newsview.model.Article;
import com.sifatullahchowdhury.rokomari.newsview.preference.PreferenceManager;
import com.sifatullahchowdhury.rokomari.newsview.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import static com.sifatullahchowdhury.rokomari.newsview.utility.Utility.NEWS_OF_COUNTRY;
import static com.sifatullahchowdhury.rokomari.newsview.utility.Utility.NEWS_OF_CNN;
import static com.sifatullahchowdhury.rokomari.newsview.utility.Utility.NEWS_OF_MIRROR;
import static com.sifatullahchowdhury.rokomari.newsview.utility.Utility.NEWS_OF_SOURCE;
import static com.sifatullahchowdhury.rokomari.newsview.utility.Utility.NEWS_OF_SPORTS;
import static com.sifatullahchowdhury.rokomari.newsview.utility.Utility.NEWS_OF_TECH;
import static com.sifatullahchowdhury.rokomari.newsview.utility.Utility.NEWS_OF_TOPIC;
import static com.sifatullahchowdhury.rokomari.newsview.utility.Utility.NEWS_OF_UK;
import static com.sifatullahchowdhury.rokomari.newsview.utility.Utility.NEWS_OF_US;

public class HomeActivity extends AppCompatActivity implements ArticleListContract.View,NavigationView.OnNavigationItemSelectedListener  {

    private static final String TAG = "HomeActivity";
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private GoogleSignInClient mGoogleSignInClient;

    int toggle=1;

    private ArticleListPresenter articleListPresenter;
    private RecyclerView rvArticles;
    private RecyclerView rvArticles2;
    private Button btn_us,btn_uk,btn_sports,btn_tech, btn_cnn,btn_mirror;
    private LinearLayout btnContainer;

    private ProgressBar pbLoading;


    private ActionBarDrawerToggle mDrawerToggle;
    private int RC_SIGN_IN=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleListPresenter = new ArticleListPresenter(this);

        getSupportActionBar().setTitle(getString(R.string.latest_news));
        initUI();
        drawer();
        GSO();          //google sign in





    }

    private void initUI() {

        rvArticles = findViewById(R.id.rv_articles);
        rvArticles2 = findViewById(R.id.rv_articles_2);




        pbLoading = findViewById(R.id.pb_loading);
        btnContainer=findViewById(R.id.btn_container);

        selectionButtonsListener();
        loadArticles();



    }

    private void selectionButtonsListener(){


        btn_us=findViewById(R.id.btn_us);
        btn_uk=findViewById(R.id.btn_uk);
        btn_sports=findViewById(R.id.btn_sports);
        btn_tech=findViewById(R.id.btn_tech);
        btn_mirror=findViewById(R.id.btn_mirror);
        btn_cnn =findViewById(R.id.btn_cnn);


        ArrayList<Button> buttons=new ArrayList<>();
        buttons.add(btn_us);
        buttons.add(btn_uk);
        buttons.add(btn_sports);
        buttons.add(btn_tech);
        buttons.add(btn_mirror);
        buttons.add(btn_cnn);

        for (Button button:buttons)
        {
            button.setBackground(getResources().getDrawable(R.drawable.rounded_button));
        }


        String currentSelection=PreferenceManager.getCurrentSelection();
        if (currentSelection.equals(NEWS_OF_US))
            btn_us.setBackground(getResources().getDrawable(R.drawable.rounded_button_selected));
        else if (currentSelection.equals(NEWS_OF_UK))
            btn_uk.setBackground(getResources().getDrawable(R.drawable.rounded_button_selected));
        else if (currentSelection.equals(NEWS_OF_SPORTS))
            btn_sports.setBackground(getResources().getDrawable(R.drawable.rounded_button_selected));
        else if (currentSelection.equals(NEWS_OF_TECH))
            btn_tech.setBackground(getResources().getDrawable(R.drawable.rounded_button_selected));
        else if (currentSelection.equals(NEWS_OF_CNN))
            btn_cnn.setBackground(getResources().getDrawable(R.drawable.rounded_button_selected));
        else if (currentSelection.equals(NEWS_OF_MIRROR))
            btn_mirror.setBackground(getResources().getDrawable(R.drawable.rounded_button_selected));
        else
            btn_us.setBackground(getResources().getDrawable(R.drawable.rounded_button_selected));


        btn_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.setCurrentSelection(NEWS_OF_US);
                loadArticles();


            }
        });

        btn_uk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.setCurrentSelection(NEWS_OF_UK);
                loadArticles();

            }
        });

        btn_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.setCurrentSelection(Utility.NEWS_OF_SPORTS);
                loadArticles();


            }
        });

        btn_tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.setCurrentSelection(Utility.NEWS_OF_TECH);
                loadArticles();


            }
        });

        btn_cnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.setCurrentSelection(Utility.NEWS_OF_CNN);
                loadArticles();


            }
        });

        btn_mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.setCurrentSelection(Utility.NEWS_OF_MIRROR);
                loadArticles();


            }
        });



    }




    public void loadArticles(){
        String currentSelection= PreferenceManager.getCurrentSelection();


        if (currentSelection.equals(NEWS_OF_US))
            articleListPresenter.requestDataFromServer(NEWS_OF_COUNTRY, NEWS_OF_US);

        else if (currentSelection.equals(NEWS_OF_UK))

            articleListPresenter.requestDataFromServer(NEWS_OF_COUNTRY,NEWS_OF_UK);

        else if (currentSelection.equals(NEWS_OF_SPORTS))
            articleListPresenter.requestDataFromServer(NEWS_OF_TOPIC,NEWS_OF_SPORTS);
        else if (currentSelection.equals(NEWS_OF_TECH))
            articleListPresenter.requestDataFromServer(NEWS_OF_TOPIC,NEWS_OF_TECH);
        else if (currentSelection.equals(NEWS_OF_CNN))
            articleListPresenter.requestDataFromServer(NEWS_OF_SOURCE, NEWS_OF_CNN);
        else if (currentSelection.equals(NEWS_OF_MIRROR))
            articleListPresenter.requestDataFromServer(NEWS_OF_SOURCE,NEWS_OF_MIRROR);
        else
            articleListPresenter.requestDataFromServer(NEWS_OF_COUNTRY,NEWS_OF_US);


    }


    @Override
    public void showProgress() {

        rvArticles.setVisibility(View.GONE);
        rvArticles2.setVisibility(View.GONE);
        //btnContainer.setVisibility(View.GONE);
        selectionButtonsListener();
       pbLoading.setVisibility(View.VISIBLE);
       findViewById(R.id.anchor).setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {

       pbLoading.setVisibility(View.GONE);
        btnContainer.setVisibility(View.VISIBLE);

       rvArticles.setVisibility(View.VISIBLE);
       if (toggle==1){
           rvArticles2.setVisibility(View.VISIBLE);
           findViewById(R.id.anchor).setVisibility(View.VISIBLE);

       }

    }

    @Override
    public void setDataToRecyclerView(List<Article> articles) {


        List<Article> articles1= articles.subList(0,articles.size()/2-1);
        List<Article> articles2= articles.subList(articles.size()/2,articles.size()-1);

        if (toggle==1) {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

            LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            ArticleCardRecyclerAdapter rvAdapter1 = new ArticleCardRecyclerAdapter(articles1, this);
            ArticleCardRecyclerAdapter rvAdapter2 = new ArticleCardRecyclerAdapter(articles2, this);


            rvArticles.setLayoutManager(mLayoutManager);
            rvArticles.setItemAnimator(new DefaultItemAnimator());
            rvArticles.setAdapter(rvAdapter1);


            rvArticles2.setLayoutManager(mLayoutManager2);
            rvArticles2.setItemAnimator(new DefaultItemAnimator());
            rvArticles2.setAdapter(rvAdapter2);
        }

        else {

            GridLayoutManager mGridLayoutManager=new GridLayoutManager(this,2);
            ArticleListRecyclerAdapter rvAdapter1 = new ArticleListRecyclerAdapter(articles, this);


            rvArticles.setLayoutManager(mGridLayoutManager);
            rvArticles.setItemAnimator(new DefaultItemAnimator());
            rvArticles.setAdapter(rvAdapter1);

            rvArticles2.setVisibility(View.GONE);



        }

        Log.e(TAG, String.valueOf(articles.size()));




    }

    @Override
    public void onResponseFailure(Throwable throwable) {

        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        articleListPresenter.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (mDrawerToggle.onOptionsItemSelected(menuItem)) {
            return true;
        }
        int id=menuItem.getItemId();
        switch (id) {
            case R.id.nav_home:
                mDrawerLayout.closeDrawers();

                PreferenceManager.setCurrentSelection(Utility.NEWS_OF_US);
                loadArticles();
                return true;
            case R.id.nav_about:
                mDrawerLayout.closeDrawers();
                Utility.textDialog(this,Utility.getStringResource(R.string.about),Utility.getStringResource(R.string.about_text),R.drawable.about);

                return true;

            case R.id.nav_google_sign:
               signIn(mGoogleSignInClient);

                return true;

            case R.id.nav_google_sign_out:
                signOut();

                return true;
            case R.id.nav_exit:
                try {
                    mDrawerLayout.closeDrawers();
                } catch (Exception e) {
                }

                finish();
                return true;


            default:
                return super.onOptionsItemSelected(menuItem);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.btn_reload:

                loadArticles();
                return true;

            case R.id.btn_search:

                return true;
            case R.id.btn_toggle:

                if (toggle==1)
                toggle=2;
                else toggle=1;

                loadArticles();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void drawer() {
        mDrawerLayout =  findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {



            }

            public void onDrawerOpened(View drawerView) {
                mDrawerToggle.setDrawerIndicatorEnabled(true);


            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        navigationView =  findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @SuppressLint("SetTextI18n")
    void updateUI(GoogleSignInAccount account){


        TextView logInfo=findViewById(R.id.log_in_info);
        if (account!=null){
            logInfo.setText(getString(R.string.logged_in_as)+" "+account.getDisplayName());

        }

        else {
            logInfo.setText(R.string.not_logged_in);


        }

    }
    void GSO(){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void signIn(GoogleSignInClient mGoogleSignInClient) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.

            mDrawerLayout.closeDrawers();
            updateUI(account);
            Utility.textDialog(this,Utility.getStringResource(R.string.google_sign_in),Utility.getStringResource(R.string.logged_in_as)+" "+account.getDisplayName(),R.drawable.google);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Utility.textDialog(this,Utility.getStringResource(R.string.google_sign_in),Utility.getStringResource(R.string.could_not_log),R.drawable.google);

            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void signOut() {


        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        mDrawerLayout.closeDrawers();
                        Utility.textDialog(HomeActivity.this,Utility.getStringResource(R.string.google_sign_out),Utility.getStringResource(R.string.logged_out),R.drawable.google);
                        TextView logInfo=findViewById(R.id.log_in_info);
                        logInfo.setText(Utility.getStringResource(R.string.not_logged_in));

                    }

                });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        updateUI(null);
                    }
                });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


}
