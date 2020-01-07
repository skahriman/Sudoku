package com.example.sk.mysudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    String[][] sudokuMatrix = new String[9][9];
    String[][] solutionMatrix = new String[9][9];

    Button selectedButton;
    Button oldButton;
    boolean isNumberSelected;

    int indexI;
    int indexJ;

    Button 	btn_11,	btn_12,	btn_13,	btn_14,	btn_15,	btn_16,	btn_17,	btn_18,	btn_19;
    Button 	btn_21,	btn_22,	btn_23,	btn_24,	btn_25,	btn_26,	btn_27,	btn_28,	btn_29;
    Button 	btn_31,	btn_32,	btn_33,	btn_34,	btn_35,	btn_36,	btn_37,	btn_38,	btn_39;
    Button 	btn_41,	btn_42,	btn_43,	btn_44,	btn_45,	btn_46,	btn_47,	btn_48,	btn_49;
    Button 	btn_51,	btn_52,	btn_53,	btn_54,	btn_55,	btn_56,	btn_57,	btn_58,	btn_59;
    Button 	btn_61,	btn_62,	btn_63,	btn_64,	btn_65,	btn_66,	btn_67,	btn_68,	btn_69;
    Button 	btn_71,	btn_72,	btn_73,	btn_74,	btn_75,	btn_76,	btn_77,	btn_78,	btn_79;
    Button 	btn_81,	btn_82,	btn_83,	btn_84,	btn_85,	btn_86,	btn_87,	btn_88,	btn_89;
    Button 	btn_91,	btn_92,	btn_93,	btn_94,	btn_95,	btn_96,	btn_97,	btn_98,	btn_99;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindButtons();


        Log.d(TAG, "Before reading...");

        try {
            createSudoku();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("sudoku", "Matrix: " + Arrays.deepToString(sudokuMatrix));
        Log.d("solution", "Matrix: " + Arrays.deepToString(solutionMatrix));
        assignSudokuNumbers();
    }

    public void onSelectButtonClicked(View view) {
        if (oldButton != null) {
            oldButton.setBackgroundColor(getResources().getColor(R.color.defaultButtonColor));
        }

        this.selectedButton = (Button) view;
        isNumberSelected = true;
        view.setBackgroundColor(getResources().getColor(R.color.selectedButton));

        oldButton = selectedButton;

        String resourceName = view.getResources().getResourceName(view.getId());
        String indexI = resourceName.substring(resourceName.length()-2, resourceName.length()-1);
        String indexJ = resourceName.substring(resourceName.length()-1, resourceName.length());

        this.indexI = Integer.valueOf(indexI) - 1;
        this.indexJ = Integer.valueOf(indexJ) - 1;

    }
    private void createSudoku() throws IOException {
        String line = "";
        InputStream inputStream = getAssets().open("sudokus/s01a.txt");
        InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);

        int i = 0;
        while ((line = br.readLine()) != null) {
            Log.d("line", "line: " + line);
            String[] numbers = line.split(" ");

            for (int j = 0; j < numbers.length; j++) {
                Integer number = Integer.valueOf(numbers[j]);
                if (number == 0) {
                    sudokuMatrix[i][j] = "";
                }
                else {
                    sudokuMatrix[i][j] = numbers[j];

                    Log.d("sudoku", "[" + i + "" + j + "]" + numbers[j]);
                    Log.d(TAG, "sudoku: " + Arrays.deepToString(sudokuMatrix));
                }
            }
            i++;
        }
    }

    private void createSolution() throws IOException {
        String line = "";
        InputStream inputStream = getAssets().open("solutions/s01a.txt");
        InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);

        int i = 0;
        while ((line = br.readLine()) != null) {
            Log.d("line", "line: " + line);
            String[] numbers = line.split(" ");

            for (int j = 0; j < numbers.length; j++) {
                solutionMatrix[i][j] = numbers[j];
                
                Log.d("sudoku", "["+i+""+j+"]"+numbers[j]);
                Log.d(TAG, "sudoku: " + Arrays.deepToString(solutionMatrix));
            }
            i++;
        }
    }

    public void onNumberSelected(View view) {
        if(isNumberSelected) {
            Button btn = (Button) view;
            this.selectedButton.setText(btn.getText().toString());
            sudokuMatrix[this.indexI][this.indexJ] = btn.getText().toString();
        }
    }

    public void onEraseClicked(View view) {
        if(isNumberSelected) {
            this.selectedButton.setText("");
        }
    }

    private boolean compareToSolution() throws IOException {
        createSolution();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String s1 = sudokuMatrix[i][j];
                String s2 = solutionMatrix[i][j];

                if (!s1.equals(s2)) {
                    Log.d(TAG, "solutionMatrix: " + sudokuMatrix[i][j] + ", " + solutionMatrix[i][j]);
                    return false;
                }
            }
        }
        return true;
    }

    public void onClickFinishButton(View view) throws IOException {
        boolean isSolutionCorrect = compareToSolution();
        if(isSolutionCorrect)
            Toast.makeText(this, "Congratulations", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "Wrong solution", Toast.LENGTH_SHORT).show();}
    }

    private void assignSudokuNumbers() {
        btn_11.setText(String.valueOf(this.sudokuMatrix[0][0]));
        btn_12.setText(String.valueOf(this.sudokuMatrix[0][1]));
        btn_13.setText(String.valueOf(this.sudokuMatrix[0][2]));
        btn_14.setText(String.valueOf(this.sudokuMatrix[0][3]));
        btn_15.setText(String.valueOf(this.sudokuMatrix[0][4]));
        btn_16.setText(String.valueOf(this.sudokuMatrix[0][5]));
        btn_17.setText(String.valueOf(this.sudokuMatrix[0][6]));
        btn_18.setText(String.valueOf(this.sudokuMatrix[0][7]));
        btn_19.setText(String.valueOf(this.sudokuMatrix[0][8]));

        btn_21.setText(String.valueOf(this.sudokuMatrix[1][0]));
        btn_22.setText(String.valueOf(this.sudokuMatrix[1][1]));
        btn_23.setText(String.valueOf(this.sudokuMatrix[1][2]));
        btn_24.setText(String.valueOf(this.sudokuMatrix[1][3]));
        btn_25.setText(String.valueOf(this.sudokuMatrix[1][4]));
        btn_26.setText(String.valueOf(this.sudokuMatrix[1][5]));
        btn_27.setText(String.valueOf(this.sudokuMatrix[1][6]));
        btn_28.setText(String.valueOf(this.sudokuMatrix[1][7]));
        btn_29.setText(String.valueOf(this.sudokuMatrix[1][8]));

        btn_31.setText(String.valueOf(this.sudokuMatrix[2][0]));
        btn_32.setText(String.valueOf(this.sudokuMatrix[2][1]));
        btn_33.setText(String.valueOf(this.sudokuMatrix[2][2]));
        btn_34.setText(String.valueOf(this.sudokuMatrix[2][3]));
        btn_35.setText(String.valueOf(this.sudokuMatrix[2][4]));
        btn_36.setText(String.valueOf(this.sudokuMatrix[2][5]));
        btn_37.setText(String.valueOf(this.sudokuMatrix[2][6]));
        btn_38.setText(String.valueOf(this.sudokuMatrix[2][7]));
        btn_39.setText(String.valueOf(this.sudokuMatrix[2][8]));

        btn_41.setText(String.valueOf(this.sudokuMatrix[3][0]));
        btn_42.setText(String.valueOf(this.sudokuMatrix[3][1]));
        btn_43.setText(String.valueOf(this.sudokuMatrix[3][2]));
        btn_44.setText(String.valueOf(this.sudokuMatrix[3][3]));
        btn_45.setText(String.valueOf(this.sudokuMatrix[3][4]));
        btn_46.setText(String.valueOf(this.sudokuMatrix[3][5]));
        btn_47.setText(String.valueOf(this.sudokuMatrix[3][6]));
        btn_48.setText(String.valueOf(this.sudokuMatrix[3][7]));
        btn_49.setText(String.valueOf(this.sudokuMatrix[3][8]));

        btn_51.setText(String.valueOf(this.sudokuMatrix[4][0]));
        btn_52.setText(String.valueOf(this.sudokuMatrix[4][1]));
        btn_53.setText(String.valueOf(this.sudokuMatrix[4][2]));
        btn_54.setText(String.valueOf(this.sudokuMatrix[4][3]));
        btn_55.setText(String.valueOf(this.sudokuMatrix[4][4]));
        btn_56.setText(String.valueOf(this.sudokuMatrix[4][5]));
        btn_57.setText(String.valueOf(this.sudokuMatrix[4][6]));
        btn_58.setText(String.valueOf(this.sudokuMatrix[4][7]));
        btn_59.setText(String.valueOf(this.sudokuMatrix[4][8]));

        btn_61.setText(String.valueOf(this.sudokuMatrix[5][0]));
        btn_62.setText(String.valueOf(this.sudokuMatrix[5][1]));
        btn_63.setText(String.valueOf(this.sudokuMatrix[5][2]));
        btn_64.setText(String.valueOf(this.sudokuMatrix[5][3]));
        btn_65.setText(String.valueOf(this.sudokuMatrix[5][4]));
        btn_66.setText(String.valueOf(this.sudokuMatrix[5][5]));
        btn_67.setText(String.valueOf(this.sudokuMatrix[5][6]));
        btn_68.setText(String.valueOf(this.sudokuMatrix[5][7]));
        btn_69.setText(String.valueOf(this.sudokuMatrix[5][8]));

        btn_71.setText(String.valueOf(this.sudokuMatrix[6][0]));
        btn_72.setText(String.valueOf(this.sudokuMatrix[6][1]));
        btn_73.setText(String.valueOf(this.sudokuMatrix[6][2]));
        btn_74.setText(String.valueOf(this.sudokuMatrix[6][3]));
        btn_75.setText(String.valueOf(this.sudokuMatrix[6][4]));
        btn_76.setText(String.valueOf(this.sudokuMatrix[6][5]));
        btn_77.setText(String.valueOf(this.sudokuMatrix[6][6]));
        btn_78.setText(String.valueOf(this.sudokuMatrix[6][7]));
        btn_79.setText(String.valueOf(this.sudokuMatrix[6][8]));

        btn_81.setText(String.valueOf(this.sudokuMatrix[7][0]));
        btn_82.setText(String.valueOf(this.sudokuMatrix[7][1]));
        btn_83.setText(String.valueOf(this.sudokuMatrix[7][2]));
        btn_84.setText(String.valueOf(this.sudokuMatrix[7][3]));
        btn_85.setText(String.valueOf(this.sudokuMatrix[7][4]));
        btn_86.setText(String.valueOf(this.sudokuMatrix[7][5]));
        btn_87.setText(String.valueOf(this.sudokuMatrix[7][6]));
        btn_88.setText(String.valueOf(this.sudokuMatrix[7][7]));
        btn_89.setText(String.valueOf(this.sudokuMatrix[7][8]));

        btn_91.setText(String.valueOf(this.sudokuMatrix[8][0]));
        btn_92.setText(String.valueOf(this.sudokuMatrix[8][1]));
        btn_93.setText(String.valueOf(this.sudokuMatrix[8][2]));
        btn_94.setText(String.valueOf(this.sudokuMatrix[8][3]));
        btn_95.setText(String.valueOf(this.sudokuMatrix[8][4]));
        btn_96.setText(String.valueOf(this.sudokuMatrix[8][5]));
        btn_97.setText(String.valueOf(this.sudokuMatrix[8][6]));
        btn_98.setText(String.valueOf(this.sudokuMatrix[8][7]));
        btn_99.setText(String.valueOf(this.sudokuMatrix[8][8]));
    }

    private void bindButtons() {
        btn_11	= findViewById	(R.id.id_11);
        btn_12	= findViewById	(R.id.id_12);
        btn_13	= findViewById	(R.id.id_13);
        btn_14	= findViewById	(R.id.id_14);
        btn_15	= findViewById	(R.id.id_15);
        btn_16	= findViewById	(R.id.id_16);
        btn_17	= findViewById	(R.id.id_17);
        btn_18	= findViewById	(R.id.id_18);
        btn_19	= findViewById	(R.id.id_19);
        btn_21	= findViewById	(R.id.id_21);
        btn_22	= findViewById	(R.id.id_22);
        btn_23	= findViewById	(R.id.id_23);
        btn_24	= findViewById	(R.id.id_24);
        btn_25	= findViewById	(R.id.id_25);
        btn_26	= findViewById	(R.id.id_26);
        btn_27	= findViewById	(R.id.id_27);
        btn_28	= findViewById	(R.id.id_28);
        btn_29	= findViewById	(R.id.id_29);
        btn_31	= findViewById	(R.id.id_31);
        btn_32	= findViewById	(R.id.id_32);
        btn_33	= findViewById	(R.id.id_33);
        btn_34	= findViewById	(R.id.id_34);
        btn_35	= findViewById	(R.id.id_35);
        btn_36	= findViewById	(R.id.id_36);
        btn_37	= findViewById	(R.id.id_37);
        btn_38	= findViewById	(R.id.id_38);
        btn_39	= findViewById	(R.id.id_39);
        btn_41	= findViewById	(R.id.id_41);
        btn_42	= findViewById	(R.id.id_42);
        btn_43	= findViewById	(R.id.id_43);
        btn_44	= findViewById	(R.id.id_44);
        btn_45	= findViewById	(R.id.id_45);
        btn_46	= findViewById	(R.id.id_46);
        btn_47	= findViewById	(R.id.id_47);
        btn_48	= findViewById	(R.id.id_48);
        btn_49	= findViewById	(R.id.id_49);
        btn_51	= findViewById	(R.id.id_51);
        btn_52	= findViewById	(R.id.id_52);
        btn_53	= findViewById	(R.id.id_53);
        btn_54	= findViewById	(R.id.id_54);
        btn_55	= findViewById	(R.id.id_55);
        btn_56	= findViewById	(R.id.id_56);
        btn_57	= findViewById	(R.id.id_57);
        btn_58	= findViewById	(R.id.id_58);
        btn_59	= findViewById	(R.id.id_59);
        btn_61	= findViewById	(R.id.id_61);
        btn_62	= findViewById	(R.id.id_62);
        btn_63	= findViewById	(R.id.id_63);
        btn_64	= findViewById	(R.id.id_64);
        btn_65	= findViewById	(R.id.id_65);
        btn_66	= findViewById	(R.id.id_66);
        btn_67	= findViewById	(R.id.id_67);
        btn_68	= findViewById	(R.id.id_68);
        btn_69	= findViewById	(R.id.id_69);
        btn_71	= findViewById	(R.id.id_71);
        btn_72	= findViewById	(R.id.id_72);
        btn_73	= findViewById	(R.id.id_73);
        btn_74	= findViewById	(R.id.id_74);
        btn_75	= findViewById	(R.id.id_75);
        btn_76	= findViewById	(R.id.id_76);
        btn_77	= findViewById	(R.id.id_77);
        btn_78	= findViewById	(R.id.id_78);
        btn_79	= findViewById	(R.id.id_79);
        btn_81	= findViewById	(R.id.id_81);
        btn_82	= findViewById	(R.id.id_82);
        btn_83	= findViewById	(R.id.id_83);
        btn_84	= findViewById	(R.id.id_84);
        btn_85	= findViewById	(R.id.id_85);
        btn_86	= findViewById	(R.id.id_86);
        btn_87	= findViewById	(R.id.id_87);
        btn_88	= findViewById	(R.id.id_88);
        btn_89	= findViewById	(R.id.id_89);
        btn_91	= findViewById	(R.id.id_91);
        btn_92	= findViewById	(R.id.id_92);
        btn_93	= findViewById	(R.id.id_93);
        btn_94	= findViewById	(R.id.id_94);
        btn_95	= findViewById	(R.id.id_95);
        btn_96	= findViewById	(R.id.id_96);
        btn_97	= findViewById	(R.id.id_97);
        btn_98	= findViewById	(R.id.id_98);
        btn_99	= findViewById	(R.id.id_99);
    }
}
