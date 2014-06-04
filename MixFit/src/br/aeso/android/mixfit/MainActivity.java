package br.aeso.android.mixfit;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.Time;
import android.view.Menu;//
import android.view.View;
import android.widget.EditText;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

    String NomeBanco = "Cadastro";
    String NomeBanco2 = "Resultado";
    SQLiteDatabase BancoDados = null;
    EditText Nome, Altura, Peso;
    Button Salvar, Consultar;
    Cursor cursor;
    ListView MostraDado;
    SimpleCursorAdapter AdapterLista;
    int x;
       
    public static final String KEY_NOMEPESSOA = "nome";
    public static final String KEY_ALTURA = "altura";
    public static final String KEY_PESO = "peso";
    public static final String KEY_IMC = "imc";
    public static final String KEY_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        Nome = (EditText) findViewById(R.id.etNome);
        Altura = (EditText) findViewById(R.id.etAltura);
        Peso = (EditText) findViewById(R.id.etPeso);
        Salvar = (Button) findViewById(R.id.btnSalvar);
        Consultar = (Button) findViewById(R.id.btnConsultar);
       
        /*Time time = new Time();
        time.setToNow();
        String data = time.format("dd/MM/yyyy");*/
       
        //CarregaDado();
        CriaBanco();
        //CriaBanco2();
        //GravaBanco();
        btnSalvar();
        btnConsultar();
    }
   
    public void CriaBanco(){
        try{
            BancoDados = openOrCreateDatabase(NomeBanco, MODE_WORLD_READABLE, null);
            String SQL = "CREATE TABLE IF NOT EXISTS tabCadastro (_id INTEGER PRIMARY KEY, nome TEXT, altura TEXT, peso TEXT, imc TEXT, data TEXT)";
            //String SQL2 = "DELETE FROM tabCadastro";
            //String SQL3 = "DROP TABLE tabCadastro";
            BancoDados.execSQL(SQL);
            //BancoDados.execSQL(SQL2);
            //BancoDados.execSQL(SQL3);
            MensagemAlerta("Banco de Dados", "Banco criado com sucesso");
        }catch(Exception erro){
            MensagemAlerta("Erro Banco de Dados", "Não foi possível criar o banco" + erro);
        }
        finally{
            BancoDados.close();
        }
    }
   
    public void CriaBanco2(){
        try{
            BancoDados = openOrCreateDatabase(NomeBanco2, MODE_WORLD_READABLE, null);
            String SQL = "CREATE TABLE IF NOT EXISTS tabResultado (_id INTEGER PRIMARY KEY, numero TEXT, texto TEXT, dica TEXT)";
            BancoDados.execSQL(SQL);
            String SQL2 = "INSERT INTO tabResultado (numero, texto) VALUES ('1', 'Muito abaixo do pesoS')";
            BancoDados.execSQL(SQL2);
            String SQL3 = "INSERT INTO tabResultado (numero, texto) VALUES ('2', 'Abaixo do peso')";
            BancoDados.execSQL(SQL3);
            String SQL4 = "INSERT INTO tabResultado (numero, texto) VALUES ('3', 'Peso normal')";
            BancoDados.execSQL(SQL4);
            String SQL5 = "INSERT INTO tabResultado (numero, texto) VALUES ('4', 'Acima do peso')";
            BancoDados.execSQL(SQL5);
            String SQL6 = "INSERT INTO tabResultado (numero, texto) VALUES ('5', 'Obesidade I')";
            BancoDados.execSQL(SQL6);
            String SQL7 = "INSERT INTO tabResultado (numero, texto) VALUES ('6', 'Obesidade II (severa)')";
            BancoDados.execSQL(SQL7);
            String SQL8 = "INSERT INTO tabResultado (numero, texto) VALUES ('7', 'Obesidade III (mórbida)')";
            BancoDados.execSQL(SQL8);
            MensagemAlerta("Banco de Dados 2", "Banco 2 criado com sucesso");
        }catch(Exception erro){
            MensagemAlerta("Erro Banco de Dados 2", "Não foi possível criar o banco 2" + erro);
        }
        finally{
            BancoDados.close();
        }
    }
   
    public void GravaBanco(){
        try{       
            //Calendar c = Calendar.getInstance();
            //Time time = new Time();
            //time.setToNow();
            //String data = time.format("dd/MM/yyyy");
        	float altura = Float.valueOf(Altura.getText().toString());
            float peso = Float.valueOf(Peso.getText().toString());
            float result = peso/(altura*altura);
            BancoDados = openOrCreateDatabase(NomeBanco, MODE_WORLD_READABLE, null);
            String SQL = "INSERT INTO tabCadastro (nome, altura, peso, imc, data) VALUES ('"+Nome.getText().toString()+"', '"+Altura.getText().toString()+"', '"+Peso.getText().toString()+"', "+result+", datetime())";
            BancoDados.execSQL(SQL);
            MensagemAlerta("Banco de Dados", "Registro criado com sucesso");           
        }catch(Exception erro){
            MensagemAlerta("Erro Banco de Dados", "Erro: " + erro);
        }
        finally{
            BancoDados.close();
        }
    }
   
    /*public void CalculaIMC(){
        float altura = Float.valueOf(Altura.getText().toString());
        float peso = Float.valueOf(Peso.getText().toString());
        float result = peso/(altura*altura);
        String a=Altura.getText().toString();       //this will get a string                              
        int a2=Integer.parseInt(a);              //this will get a no from the string           
        MensagemAlerta("Calculo", "Seu IMC é: " + result);
        String SQL = "SELECT * FROM tabCadastro";
        BancoDados.execSQL(SQL);
        MensagemAlerta("Consulta", "isso" + SQL);
    }*/
   
    private boolean VerificaRegistro(String nome){
        try{
        BancoDados = openOrCreateDatabase(NomeBanco, MODE_WORLD_READABLE, null);
        if (nome != null && nome.trim().length()>0){
            cursor = BancoDados.rawQuery("select * from tabCadastro where nome = '"+nome+"'", null);
        }else{   
            cursor = BancoDados.rawQuery("select * from tabCadastro", null);
        }   
       
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            return true;
        }else{
            return false;
        }
        }catch(Exception erro){
            MensagemAlerta("Erro Banco de Dados", "Não foi possível verificar dados" + erro);
            return false;
            }
        finally{
            BancoDados.close();
        }
        }
        //BancoDados.query
   
    public void CarregaDado(String nome){
        MostraDado =(ListView) findViewById(R.id.lvMostraDados);
       
        if (VerificaRegistro(nome)){
            String [] Coluna = new String[] {KEY_NOMEPESSOA, KEY_ALTURA, KEY_PESO, KEY_IMC, KEY_DATA};
           
            AdapterLista = new SimpleCursorAdapter(this, R.layout.mostrabanco, cursor, Coluna, new int[] {R.id.tvNome2, R.id.tvAltura2, R.id.tvPeso2, R.id.tvIMC, R.id.tvData});
            MostraDado.setAdapter(AdapterLista);
            //MensagemAlerta("Consulta", "Erro ao tentar consultar os dados");
        }
        else{
            MensagemAlerta("Erro Banco de Dados", "Você não possui nenhum cadastro");
        }
    }
   
    public void btnSalvar(){
        Salvar.setOnClickListener(new View.OnClickListener() {
           
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                GravaBanco();
                //CalculaIMC();               
               
                //MensagemAlerta("Banco de Dados", "Registro salvo com sucesso");
            }
        });
    }
   
    public void btnConsultar(){
        Consultar.setOnClickListener(new View.OnClickListener() {
           
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                CarregaDado(Nome.getText().toString());
            }
        });
    }
   
    public void MensagemAlerta(String TituloAlerta, String MensagemAlerta){
        AlertDialog.Builder Mensagem = new AlertDialog.Builder(MainActivity.this);
        Mensagem.setTitle(TituloAlerta);
        Mensagem.setMessage(MensagemAlerta);
        Mensagem.setNeutralButton("OK", null);
        Mensagem.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   
}
