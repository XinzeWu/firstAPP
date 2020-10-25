package com.example.myfirstapp;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    //public static final String EXTRA_MESSAGE_EXPLAIN = "com.example.myfirstapp.MESSAGE_EXPLAIN";
//    public static data guaData; //我不懂这里为什么不要实例化

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


//    EditText editText = (EditText) findViewById(R.id.editText);

    //editText.addTextChangedListener(new TextWatcher(){
    /*
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if(!editText.getText().toString().equals("输入您想占卜的对象")){
                editText.setText("");

            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    //editText.addTextChangedListener(textWatcher);
*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendMessage(View view) {
        //EditText editText = findViewById(R.id.editText);
        change();
        setContentView(R.layout.activity_display_message);
        TextView textView1 = findViewById(R.id.gua_name);
        textView1.setText(data.guaName);

        TextView textView2 = findViewById(R.id.yao_name);
        textView2.setText(data.changeYao);

        TextView textView3 = findViewById(R.id.yao_web);
        textView3.setText(data.guaExp);


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getBack(View view){
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void change(){
        int[] Yao = new int[6];//储存阴阳余数
        int[] yao = new int[6];//储存阴阳0为阴，1为阳，第一次算得的爻需要置于卦尾

        for(int i = 0; i < 6;++i){ //每次是一爻
            int all = 50;
            int all_left = all - 1;
            all_left = changeonce(all_left);//一变
            all_left = changeonce(all_left);//二变
            all_left = changeonce(all_left);//三变
            all_left = all_left/4;
            Yao[i] = all_left;

        }

        //判断几爻可变

        int yaoChangeSum = 0;//储存可变的爻
        int[] yaoIndex = new int[6];
        for(int i = 0;i<6;++i){
            if(Yao[5-i] == 6||Yao[5-i] == 9){
                yaoIndex[yaoChangeSum] = i;
                yaoChangeSum = yaoChangeSum + 1;
            }
        }
        String yaoChange = "可变的爻从上至下为：";
        for(int i = 0;i<yaoChangeSum;++i){
            yaoChange = yaoChange.concat(String.valueOf(yaoIndex[i]+1));//一是最小的
            yaoChange = yaoChange.concat(" ");
        }

        //变卦的问题
        switch(yaoChangeSum){
            case 0 : {yaoChange = yaoChange.concat(" 无\n采用本卦");break;}
            case 1 : {yaoChange = yaoChange.concat("\n采用本卦的变爻");break;}
            case 2 : {yaoChange = yaoChange.concat("\n用上一爻的卦辞");break;}
            case 3 : {yaoChange = yaoChange.concat("\n本卦与之卦结合");break;}
            case 4 : {yaoChange = yaoChange.concat("\n采用之卦中不变的二爻,下爻为主");break;}
            case 5 : {yaoChange = yaoChange.concat("\n用之卦不变的一爻");break;}
            case 6 : {yaoChange = yaoChange.concat("\n乾坤变用爻，其余用之卦");break;}

        }


        for(int i = 0;i<6;++i){
            //System.out.println(Yao[5-i]);
            if (Yao[5-i]==9||Yao[5-i]==7){
                yao[5-i] = 1;
            }
            else{
                yao[5-i] = 0;
            }
        }

        List<String> gua = new ArrayList<String>();
        for(int i = 0;i<6;++i){
            gua.add(String.valueOf(yao[5-i]));
        }
        //Gua和gua实际上是一个，只是为了改一下类型，为下文的switch铺垫
         String Gua = String.join("",gua);
         String gua_res = chainese_character(Gua);
         gua_res = "您占卜的结果是： "+gua_res;
         //String gua_fin = "您占卜到的卦为： "+gua_res+ "\n"+yaoChange;
        data.guaName = gua_res;
        data.changeYao = yaoChange;
        gua_exp();
        //return gua_fin;
    }

    //功能函数
    public static int changeonce(int all_left) {
        Random random = new Random();//指定种子数字
        int block1 = random.nextInt(all_left-1) + 1;
        int block2 = all_left - block1;
        int get1or2 = random.nextInt(2)+1;
        if (get1or2 == 1){
            block1 = block1 - 1;
        }
        else{
            block2 = block2 - 1;
        }

        int left1;
        if(block1%4 == 0){
            left1 = 4;
        }
        else{
            left1 = block1%4;
        }

        int left2;
        if(block2%4 == 0){
            left2 = 4;
        }
        else{
            left2 = block2%4;
        }
        return (all_left - (left1+left2) - 1);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String chainese_character(String Gua){ //返回一个卦的名字
        String chainses_gua;
        switch(Gua) {
            case "111111":
                chainses_gua = "乾";
                break;
            case "000000":
                chainses_gua = "坤";
                break;
            case "010001":
                chainses_gua = "屯";
                break;
            case "100010":
                chainses_gua = "蒙";
                break;
            case "010111":
                chainses_gua = "需";
                break;
            case "111010":
                chainses_gua = "讼";
                break;
            case "000010":
                chainses_gua = "师";
                break;
            case "010000":
                chainses_gua = "比";
                break;
            case "110111":
                chainses_gua = "小畜";
                break;
            case "111011":
                chainses_gua = "履";
                break;
            case "000111":
                chainses_gua = "泰";
                break;
            case "111000":
                chainses_gua = "否";
                break;
            case "111101":
                chainses_gua = "同人";
                break;
            case "101111":
                chainses_gua = "大有";
                break;
            case "000100":
                chainses_gua = "谦";
                break;
            case "001000":
                chainses_gua = "豫";
                break;
            case "011001":
                chainses_gua = "随";
                break;
            case "100110":
                chainses_gua = "蛊";
                break;
            case "000011":
                chainses_gua = "临";
                break;
            case "110000":
                chainses_gua = "观";
                break;
            case "101001":
                chainses_gua = "噬嗑";
                break;
            case "100101":
                chainses_gua = "贲";
                break;
            case "100000":
                chainses_gua = "剥";
                break;
            case "000001":
                chainses_gua = "复";
                break;
            case "111001":
                chainses_gua = "无妄";
                break;
            case "100111":
                chainses_gua = "大畜";
                break;
            case "100001":
                chainses_gua = "颐";
                break;
            case "011110":
                chainses_gua = "大过";
                break;
            case "010010":
                chainses_gua = "坎";
                break;
            case "101101":
                chainses_gua = "离";
                break;
            case "011100":
                chainses_gua = "咸";
                break;
            case "001110":
                chainses_gua = "恒";
                break;
            case "111100":
                chainses_gua = "遁";
                break;
            case "001111":
                chainses_gua = "大壮";
                break;
            case "101000":
                chainses_gua = "晋";
                break;
            case "000101":
                chainses_gua = "明夷";
                break;
            case "110101":
                chainses_gua = "家人";
                break;
            case "101011":
                chainses_gua = "睽";
                break;
            case "010100":
                chainses_gua = "蹇"; //读音为jian(3
                break;
            case "001010":
                chainses_gua = "解";
                break;
            case "100011":
                chainses_gua = "损";
                break;
            case "110001":
                chainses_gua = "益";
                break;
            case "011111":
                chainses_gua = "夬"; //读音guai(4
                break;
            case "111110":
                chainses_gua = "姤";//读音gou(4
                break;
            case "011000":
                chainses_gua = "萃";
                break;
            case "011010":
                chainses_gua = "困";
                break;
            case "010110":
                chainses_gua = "井";
                break;
            case "011101":
                chainses_gua = "革";
                break;
            case "101110":
                chainses_gua = "鼎";
                break;
            case "001001":
                chainses_gua = "震";
                break;
            case "100100":
                chainses_gua = "艮";
                break;
            case "110100":
                chainses_gua = "渐";
                break;
            case "001011":
                chainses_gua = "归妹";
                break;
            case "001101":
                chainses_gua = "丰";
                break;
            case "101100":
                chainses_gua = "旅";
                break;
            case "110110":
                chainses_gua = "巽";//读音xun(4
                break;
            case "011011":
                chainses_gua = "兑";
                break;
            case "110010":
                chainses_gua = "涣";
                break;
            case "010011":
                chainses_gua = "节";
                break;
            case "110011":
                chainses_gua = "中孚";
                break;
            case "001100":
                chainses_gua = "小过";
                break;
            case "010101":
                chainses_gua = "既济";
                break;
            case "101010":
                chainses_gua = "未济";
                break;
            case "000110":
                chainses_gua = "升";
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + Gua);
        }
        return chainses_gua;

    }

    public static void gua_exp(){
        data.guaExp = "没戏了";
    }

}


