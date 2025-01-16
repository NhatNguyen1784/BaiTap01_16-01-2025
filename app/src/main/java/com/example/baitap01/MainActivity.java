package com.example.baitap01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText etSize;
    private Button btnGenerate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Khởi tạo các thành phần giao diện
        etSize = findViewById(R.id.etSize);
        btnGenerate = findViewById(R.id.btnGenerate);
        tvResult = findViewById(R.id.tvResult);

        // Tạo mảng ArrayList bằng random (cho mảng ban đầu)
        ArrayList<Integer> numberList = generateRandomNumbers(10, 1, 100); // 10 số random từ 1 đến 100

        // Log các số trong mảng
        Log.d("PrimeNumberChecker", "Mảng các số đã random: " + numberList);

        // Kiểm tra và in các số nguyên tố trong mảng
        Log.d("PrimeNumberChecker", "Các số nguyên tố trong mảng là:");
        for (int number : numberList) {
            if (isPrime(number)) {
                Log.d("PrimeNumberChecker", String.valueOf(number));
            }
        }

        // Xử lý sự kiện cho nút Button
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy số phần tử từ EditText
                String sizeText = etSize.getText().toString();
                if (sizeText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số phần tử!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int size = Integer.parseInt(sizeText);

                // Tạo mảng ngẫu nhiên
                ArrayList<Integer> randomNumbers = generateRandomNumbers(size, 1, 100);

                // Kiểm tra các số chính phương trong mảng
                ArrayList<Integer> squareNumbers = new ArrayList<>();
                for (int number : randomNumbers) {
                    if (isPerfectSquare(number)) {
                        squareNumbers.add(number);
                    }
                }

                // Hiển thị kết quả trong TextView và Toast
                if (squareNumbers.isEmpty()) {
                    tvResult.setText("Không có số chính phương!");
                    Toast.makeText(MainActivity.this, "Không có số chính phương!", Toast.LENGTH_SHORT).show();
                } else {
                    tvResult.setText("Các số chính phương trong mảng: " + squareNumbers.toString());
                    Toast.makeText(MainActivity.this, "Có số chính phương!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý cửa sổ
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Hàm tạo danh sách các số ngẫu nhiên
     *
     * @param size  Số lượng phần tử
     * @param min   Giá trị nhỏ nhất
     * @param max   Giá trị lớn nhất
     * @return ArrayList chứa các số ngẫu nhiên
     */
    private ArrayList<Integer> generateRandomNumbers(int size, int min, int max) {
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int randomNumber = random.nextInt((max - min) + 1) + min;
            randomNumbers.add(randomNumber);
        }

        return randomNumbers;
    }

    /**
     * Hàm kiểm tra số chính phương
     *
     * @param number Số cần kiểm tra
     * @return true nếu là số chính phương, ngược lại false
     */
    private boolean isPerfectSquare(int number) {
        double sqrt = Math.sqrt(number);
        return sqrt == Math.floor(sqrt);
    }

    /**
     * Hàm kiểm tra số nguyên tố
     *
     * @param number Số cần kiểm tra
     * @return true nếu là số nguyên tố, ngược lại false
     */
    private boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}