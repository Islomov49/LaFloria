package com.iso.developer.lafloria.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.iso.developer.lafloria.FloriaActivity;
import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.utils.ConstantsFl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static android.content.Context.MODE_PRIVATE;

public class AddingProductFragment extends Fragment {
    private final int PERMISSION_READ_STORAGE = 6;
    public static int RESULT_LOAD_IMAGE = 1;
    ImageView imageProduct;
    String photoPath;
    Spinner spTypeOfAdding;
    RadioGroup radioGroup;
    EditText etPhoneNumber;
    SharedPreferences sharedPreferences;
    public AddingProductFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences =  getActivity().getSharedPreferences(ConstantsFl.SHARED_PREF_NAME,MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adding_product, container, false);
        imageProduct = (ImageView) view.findViewById(R.id.imageSelect);
        spTypeOfAdding = (Spinner) view.findViewById(R.id.spTypeOfAdding);
        radioGroup = (RadioGroup) view.findViewById(R.id.rgTypes);
        etPhoneNumber = (EditText) view.findViewById(R.id.etPhoneNumber);
        //Types of sub
        String[] types = { "Exclusive","101 Rose","Violeta","Card bouqete"};
        ArrayAdapter<String> adapter_FirstType = new ArrayAdapter<String>(getActivity(),
                R.layout.spiner_gravity_right, types);
        spTypeOfAdding.setAdapter(adapter_FirstType);
        String adminPhone = sharedPreferences.getString(ConstantsFl.ADMIN_PHONE_NUMBER,"");
        if(!adminPhone.equals("")){
            etPhoneNumber.setText(adminPhone.replace(',','.'));
        }

        view.findViewById(R.id.mainAddingView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbBouqete:
                        String[] typeBouqete = { "Exclusive","101 Rose","Violeta","Card bouqete"};
                        ArrayAdapter<String> adapter_Bouqete = new ArrayAdapter<String>(getActivity(),
                                R.layout.spiner_gravity_right, typeBouqete);
                        spTypeOfAdding.setAdapter(adapter_Bouqete);
                        break;
                    case R.id.rbRomantic:
                        String[] typeRomantic = { "Kinder","Rafaello","Red Lips","Cooler"};
                        ArrayAdapter<String> adapter_Romantic = new ArrayAdapter<String>(getActivity(),
                                R.layout.spiner_gravity_right, typeRomantic);
                        spTypeOfAdding.setAdapter(adapter_Romantic);
                        break;
                    case R.id.rbService:
                        String[] typeService = { "Cartege","Home sweet","Relaxing"};
                        ArrayAdapter<String> adapter_Service = new ArrayAdapter<String>(getActivity(),
                                R.layout.spiner_gravity_right, typeService);
                        spTypeOfAdding.setAdapter(adapter_Service);
                        break;

                }
            }
        });
        imageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int permission = ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((getActivity()),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage(getString(R.string.permision_dialog))
                                .setTitle(getString(R.string.permision_requred));
                        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        PERMISSION_READ_STORAGE);
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                PERMISSION_READ_STORAGE);
                    }
                } else {
                    getPhoto();
                }

            }
        });
        view.findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveForNextTimePhoneNumber
                if(etPhoneNumber.getText().toString().length()!=0)
               sharedPreferences.edit().putString(ConstantsFl.ADMIN_PHONE_NUMBER,etPhoneNumber.getText().toString().replace(',','.')).commit();

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    private void getPhoto() {

        Intent i = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            photoPath = picturePath;

            Bitmap bitmap = decodeFile(new File(photoPath));
            Bitmap C;
            if (bitmap.getWidth() >= bitmap.getHeight()) {
                C = Bitmap.createBitmap(
                        bitmap,
                        bitmap.getWidth() / 2 - bitmap.getHeight() / 2,
                        0,
                        bitmap.getHeight(),
                        bitmap.getHeight()
                );
            } else {
                C = Bitmap.createBitmap(
                        bitmap,
                        0,
                        bitmap.getHeight() / 2 - bitmap.getWidth() / 2,
                        bitmap.getWidth(),
                        bitmap.getWidth()
                );
            }


            imageProduct.setImageBitmap(C);

        }
    }

    private Bitmap decodeFile(File f) {
        try {
//            Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
//            The new size we want to scale to
            final int REQUIRED_SIZE = 256;
//            Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;
            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
