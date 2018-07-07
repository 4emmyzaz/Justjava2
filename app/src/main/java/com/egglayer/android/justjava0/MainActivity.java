package com.egglayer.android.justjava0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


// * This app displays an order form to order coffee.


public class MainActivity extends AppCompatActivity {

    //  Global Data declared
    int quantity = 1;
    String displayQuantity;
    String displayMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


//     * This method is called when the plus button is clicked.


    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText( this, "ooops!, You cannot order more than a hundred coffees ", Toast.LENGTH_SHORT).show();
            return;
        }
        {
            quantity = quantity + 1;
            displayQuantity(quantity);

            // Toast

        }
    }

//     * This method is called when the minus button is clicked.


    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText( this, "ouch!, You cannot order nothing, please grab a coffee ", Toast.LENGTH_SHORT).show();
            return;
        }
        {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    public void submitOrder(View view) {

        //@EditText for Name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String namex = nameField.getText().toString();
//            Log.v("MainActivity", "Name: " + namex);

        //@checkbox whipped ice cream
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
//        Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);

        //@checkbox for chocolate
        CheckBox chocolateCreamCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCreamCheckBox.isChecked();
//        Log.v("MainActivity", "Has chocolate cream: " + hasChocolateCream);

//        // Alternative Calclation for price coffee and extras

            int price = calculatePrice(hasWhippedCream, hasChocolate);
                    String name = createOrderSummary(namex, hasWhippedCream, hasChocolate) + "\n " +  "Total: $ " + price;
                    displayMessage(name);

        // Calculate the price  15-06-18
       /* int price = calculatePrice(hasWhippedCream, hasChocolate);
        // @Toast message

        // Display the order summary on the screen - 15-06-18
        String message = createOrderSummary(name, hasWhippedCream, hasChocolate) + "\n " +  "Total: $ " + price;
        displayMessage(name);*/

    }

/*
     * This method displays the given quantity value on the screen.
*/


    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }


    public void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    //  calculate price of a coffee
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }

    // @createOrderSummary
        private String createOrderSummary(String name, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate ? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    // @Reset Button Method
    public void resetOrder(View view) {
       TextView setZero = (TextView) findViewById(R.id.quantity_text_view);
        setZero.setText("1");
//        displayQuantity = "0";
//        displayMessage(displayQuantity);
        quantity = 1;
        displayMessage(displayQuantity);
        displayQuantity(quantity);
    }

}


