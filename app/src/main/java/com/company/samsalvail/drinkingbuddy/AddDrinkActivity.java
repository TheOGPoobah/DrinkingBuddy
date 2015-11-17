package com.company.samsalvail.drinkingbuddy;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class AddDrinkActivity extends ActionBarActivity {

    ListView list;
    String[] options = {
            "Coffee",
            "Add Drink"
    } ;

    Integer[] images = {
            R.drawable.coffee117,
            R.drawable.add

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink);

        CustomList adapter = new CustomList(AddDrinkActivity.this, options, images);
        list=(ListView)findViewById(R.id.optionsList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(Main2Activity.this, "You Clicked: " +options[+ position], Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        // BALANCE
                        //final Intent balanceIntent = new Intent(view.getContext(), MainBalanceActivity.class);
                        //startActivity(balanceIntent);
                        break;
                    case 1:
                        // TRANSFER
                        //final Intent transferIntent = new Intent(view.getContext(), MainTransferActivity.class);
                        //startActivity(transferIntent);
                        break;
                    case 2:
                        // DEPOSIT
                        //final Intent depositIntent = new Intent(view.getContext(), MainDepositActivity.class);
                        //startActivity(depositIntent);
                        break;
                    case 3:
                        // STATEMENTS
                        //final Intent statementsIntent = new Intent(view.getContext(), MainStatementsActivity.class);
                        //startActivity(statementsIntent);
                        break;
                    case 4:
                        // PAY BILLS
                        //final Intent payBillsIntent = new Intent(view.getContext(), MainPayBillsActivity.class);
                        //startActivity(payBillsIntent);
                        break;
                    case 5:
                        // ADD PAYEE
                        //final Intent payeeIntent = new Intent(view.getContext(), MainPayeeActivity.class);
                        //startActivity(payeeIntent);
                        break;
                    case 6:
                        // BRANCH LOCATOR
                        //final Intent branchLocatorIntent = new Intent(view.getContext(), MainBranchLocatorActivity.class);
                        //startActivity(branchLocatorIntent);
                        break;
                    case 7:
                        // FIND ATM
                        //final Intent findATMIntent = new Intent(view.getContext(), MainFindATMActivity.class);
                        //startActivity(findATMIntent);
                        break;
                    case 8:
                        // CONTACT US
                        //final Intent contactUsIntent = new Intent(view.getContext(), MainContactUsActivity.class);
                        //startActivity(contactUsIntent);
                        break;
                    case 9:
                        // SUPPORT
                        //final Intent supportIntent = new Intent(view.getContext(), MainSupportActivity.class);
                        //startActivity(supportIntent);
                        break;
                    case 10:
                        // SETTINGS
                        //final Intent settingsIntent = new Intent(view.getContext(), MainSettingsActivity.class);
                        //startActivity(settingsIntent);
                        break;
                    case 11:
                        // LOGOUT
//                        new AlertDialog.Builder(view.getContext())
//                                .setTitle("Logout")
//                                .setMessage("Are you sure you want to logout?")
//                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//                                    public void onClick(DialogInterface dialog, int whichButton) {
//                                        final Intent logoutIntent = new Intent(Main2Activity.this, MainActivity.class);
//                                        startActivity(logoutIntent);
//                                    }
//                                })
//                                .setNegativeButton(android.R.string.no, null).show();
//                        break;
                    default:
                        Toast.makeText(AddDrinkActivity.this, "oops", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_drink, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
