package com.Biosys.bsmobilesleuth;

import com.Biosys.Controlers.AuthenticationControler;
import com.Biosys.DaoClasses.User;
import com.Biosys.LocalDatabaseComunication.DatabaseHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add_user:
			Intent intentAdmin = new Intent(
					"com.Biosys.bsmobilesleuth.UserRegistrationActivity");
			startActivity(intentAdmin);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onLoginClickAction(View v) {
		EditText etLogin = (EditText) findViewById(R.id.editText1);
		EditText etPass = (EditText) findViewById(R.id.editText2);

		RuntimeExceptionDao<User, Integer> userDao = getHelper()
				.getUserDataDao();

		try {
			User correctUser = AuthenticationControler
					.GetUserInDatabaseByLogin(etLogin.getText().toString(),
							etPass.getText().toString(), userDao);

			if (correctUser != null) {
				SharedPreferences sp = getSharedPreferences(
						"com.biosys.mobilesleuth", Context.MODE_PRIVATE);
				SharedPreferences.Editor ed = sp.edit();

				ed.putInt("string.userId", correctUser.getMainDataBaseId());
				ed.putInt("string.userPosition",
						correctUser.getPositionGroupId());
				ed.commit();
			}

			if (correctUser != null) {
				//int loggedUserPosition = correctUser.getPositionGroupId();

				//switch (loggedUserPosition) {

				//case 1:
					Intent intent = new Intent(
							"com.Biosys.bsmobilesleuth.EmployeeActivity");
					startActivity(intent);
					//break;

				//case 2:
					//Intent intentEmployee = new Intent(
						//	"com.Biosys.bsmobilesleuth.AdminActivity");
					//startActivity(intentEmployee);
					//break;

				//case 3:
					//Intent intentTradesman = new Intent(
						//	"com.Biosys.bsmobilesleuth.AdminActivity");
					//startActivity(intentTradesman);
				//	break;

				//default:
					//Toast.makeText(this, "wrong position id", Toast.LENGTH_LONG)
						//	.show();
					//break;
			} else {
				etPass.setText("");
				Toast.makeText(this, getResources().getString(R.string.login_wrong_datas), Toast.LENGTH_LONG).show();
			}
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}
