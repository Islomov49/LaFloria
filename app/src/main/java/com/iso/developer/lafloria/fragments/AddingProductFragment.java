package com.iso.developer.lafloria.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.adapters.PhotoFiltersListAdapter;
import com.iso.developer.lafloria.datamoduls.ProductEntity;
import com.iso.developer.lafloria.syncbase.SignInGoogleMoneyHold;
import com.iso.developer.lafloria.utils.CalculateUtills;
import com.iso.developer.lafloria.utils.ConstantsFl;
import com.iso.developer.lafloria.utils.CountryUtills;
import com.iso.developer.lafloria.utils.DataTimeUtills;
import com.iso.developer.lafloria.utils.LinearManagerWithOutEx;
import com.iso.developer.lafloria.utils.FiltersCollectionByTojiev;
import com.iso.developer.lafloria.utils.PhoneUtills;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;
import static com.iso.developer.lafloria.fragments.MyMenu.accountName;
import static com.iso.developer.lafloria.fragments.MyMenu.circleImageView;
import static com.iso.developer.lafloria.fragments.MyMenu.imagetask;
import static com.iso.developer.lafloria.fragments.MyMenu.userEmail;
import static com.iso.developer.lafloria.utils.ConstantsCategory.*;

public class AddingProductFragment extends Fragment {
    private final int PERMISSION_READ_STORAGE = 6;
    public static int RESULT_LOAD_IMAGE = 1;
    SignInGoogleMoneyHold signInGoogleMoneyHold;
    ImageView imageProduct;
    String photoPath;
    Spinner spTypeOfAdding;
    Spinner spCountry;
    RadioGroup radioGroup;
    EditText etPhoneNumber;
    SharedPreferences sharedPreferences;
    RelativeLayout rlPriceView;
    FirebaseDatabase database ;
    DatabaseReference rootReference;
    ScrollView sv;
    EditText etNameOfProduct,etAmount,etTimeOfDelivery,etInfoProduct;
    TextView priceProduct;
    TextView nameProductView;
    RecyclerView photoFilters;
    RelativeLayout animateFor;
    boolean pereduprejdeniya_info = false;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference bucketReference = storage.getReferenceFromUrl("gs://lafloria-e0b36.appspot.com");
    UploadTask uploadTask;
    StorageMetadata metadata;
    Bitmap filteredBitmap;


    public AddingProductFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences =  getActivity().getSharedPreferences(ConstantsFl.SHARED_PREF_NAME,MODE_PRIVATE);
        database = FirebaseDatabase.getInstance();
        rootReference = database.getReference();
        storage = FirebaseStorage.getInstance();
        bucketReference = storage.getReferenceFromUrl("gs://lafloria-e0b36.appspot.com");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adding_product, container, false);
        imageProduct = (ImageView) view.findViewById(R.id.imageSelect);
        spTypeOfAdding = (Spinner) view.findViewById(R.id.spTypeOfAdding);
        spCountry = (Spinner) view.findViewById(R.id.spCountry);
        radioGroup = (RadioGroup) view.findViewById(R.id.rgTypes);
        photoFilters = (RecyclerView) view.findViewById(R.id.recyclerPhotoFilters);
        rlPriceView = (RelativeLayout) view.findViewById(R.id.rlPriceView);
        animateFor = (RelativeLayout) view.findViewById(R.id.animateFor);

        etNameOfProduct =(EditText) view.findViewById(R.id.etNameProduct);
        etAmount =(EditText) view.findViewById(R.id.etAmmount);
        etTimeOfDelivery =(EditText) view.findViewById(R.id.tpTimerPickerDeleivry);
        etPhoneNumber = (EditText) view.findViewById(R.id.etPhoneNumber);
        etInfoProduct = (EditText) view.findViewById(R.id.etInfoProduct);
        priceProduct = (TextView) view.findViewById(R.id.priceProduct);
        nameProductView = (TextView) view.findViewById(R.id.textView);
        sv = (ScrollView) view.findViewById(R.id.customsv);

        rlPriceView.setVisibility(View.GONE);

        //textWatcher for ammount
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text =etAmount.getText().toString();

                if(text.length() == 0){
                    priceProduct.setText("0 "+getResources().getString(R.string.valyuta));

                }
                else {

                    priceProduct.setText(CalculateUtills.ammountWithProbels(text)+" "+getResources().getString(R.string.valyuta));

                }
            }
        });
        etNameOfProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text =etNameOfProduct.getText().toString();

                if(text.length() == 0){
                    nameProductView.setText(getResources().getString(R.string.app_name));

                }
                else {

                    nameProductView.setText(text);

                }
            }
        });




        //Types of sub
        String[] country = CountryUtills.UZB_COUNTRIES;
        ArrayAdapter<String> adapter_country = new ArrayAdapter<String>(getActivity(),
                R.layout.spiner_gravity_left, country);
        spCountry.setAdapter(adapter_country);


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
                boolean ismojno = true;

                if(current_bitmap==null){
                    Runnable run1 = new TimerTask() {
                        @Override
                        public void run() {
                            animateFor.animate().setDuration(700).translationY(10).setInterpolator(new BounceInterpolator());
                        }
                    };
                    animateFor.animate().setDuration(100).translationY(-25).setInterpolator(new DecelerateInterpolator()).withEndAction(run1);
                    ismojno = false;
                }
                if(!ismojno){
//                    Toast.makeText(getContext(), getString(R.string.add_photo),Toast.LENGTH_SHORT).show();
                    sv.scrollTo(0, sv.getTop());
                    return;

                }
                long timeInterval = DataTimeUtills.isItCorrectTimeHHMM(etTimeOfDelivery.getText().toString());
                if(timeInterval ==-1){

                    etTimeOfDelivery.setError(getString(R.string.format_error));
                    ismojno = false;
                }
                else     etTimeOfDelivery.setError(null);

                String phoneNumbe = etPhoneNumber.getText().toString();
                if(!PhoneUtills.isCorrectPhoneFormat(phoneNumbe,PhoneUtills.UZB))
                {

                    etPhoneNumber.setError(getString(R.string.phone_format_error));
                    ismojno = false;
                }
                else  etPhoneNumber.setError(null);


                if(etNameOfProduct.getText().toString().length()<3 ||etNameOfProduct.getText().toString().length()>15){
                    etNameOfProduct.setError(getString(R.string.name_product_error));
                    ismojno = false;
                }
                else {
                    etNameOfProduct.setError(null);
                }

                double ammount_prodct = -1;
                if(etAmount.getText().toString().length()<3 || etAmount.getText().toString().length()>15 ){
                    etAmount.setError(getString(R.string.ammount_format_incorect));
                    ismojno = false;
                }
                else {
                    try {
                        ammount_prodct = Double.parseDouble(etAmount.getText().toString().replace(',','.'));
                        etAmount.setError(null);
                    }
                    catch (Exception o){
                        o.printStackTrace();
                        etAmount.setError(getString(R.string.ammount_format_incorect));
                        ismojno = false;
                    }

                }

                if(!ismojno){
                    sv.scrollTo(0, (int) (etNameOfProduct.getY()-60));
                    return;

                }

                if(etInfoProduct.getText().toString().length()==0){
                    if(pereduprejdeniya_info){
                        ismojno = true ;
                    }
                    else {
                        Toast.makeText(getContext(), R.string.info_obyasnawka,Toast.LENGTH_LONG).show();
                        pereduprejdeniya_info = true;
                        ismojno = false;
                    }
                }
                else if(etInfoProduct.getText().length()<10 || etInfoProduct.getText().length()>500  ){
                    etInfoProduct.setError(getString(R.string.info_error));
                    ismojno=false;
                }
                else {
                    etInfoProduct.setError(null);
                }


                if(!ismojno){
                    return;

                }


                String sub_type = spTypeOfAdding.getSelectedItem().toString();
                Log.d("EEEE", sub_type);
                    String type_operation="";
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rbBouqete:
                        type_operation = BOUQETE;
                        break;
                    case R.id.rbRomantic:
                        type_operation = ROMANTIC;
                        break;
                    case R.id.rbService:
                        type_operation = SERVICE;
                        break;
                }

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle(getContext().getString(R.string.please_wait));
                //TODO Go FIREBASE
                final String photokey = rootReference.push().getKey();

                final ProductEntity product = new ProductEntity(type_operation,sub_type, etNameOfProduct.getText().toString(),
                        ammount_prodct, spCountry.getSelectedItemPosition(),etInfoProduct.getText().toString(),timeInterval,phoneNumbe ,photokey,"","");
                final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser!=null){
                    progressDialog.show();
                    final String key = rootReference.child("Products").push().getKey();

                    metadata = new StorageMetadata.Builder()
                            .setContentType("image/jpeg")
                            .build();

                    try {
                        savePhotoToCache(photokey,filteredBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(),"Some Error with photo",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        return;
                    }


                    ////
                    Uri file = Uri.fromFile(new File( Environment.getExternalStorageDirectory().getAbsolutePath() +
                            "/LaFloria/photos/"+photokey+".jpg"));
                    final Uri file_cache = Uri.fromFile(new File( Environment.getExternalStorageDirectory().getAbsolutePath() +
                            "/LaFloria/cache/"+photokey+".jpg"));

                    uploadTask=bucketReference.child("ProductsPhoto/"+key).putFile(file,metadata);
                    uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            Log.d("fbb",progress +"");
                        }
                    }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("Upload is paused");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            bucketReference.child("ProductsCache/"+key).putFile(file_cache,metadata).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    product.setPathProductImageOne(photokey);
                                    Map<String, Object> postValues = product.toMap();
                                    Map<String, Object> childUpdates = new HashMap<>();
                                    childUpdates.put("/Products/" + key, postValues);
                                    childUpdates.put("/UserOrders/" + firebaseUser.getUid() + "/productsList/" + key, true);
                                    rootReference.updateChildren(childUpdates).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            progressDialog.dismiss();
                                        }
                                    });

                                }
                            });

                        }
                    });
                    ////



                }
                else {
                    //TODO CHALA
                    signInGoogleMoneyHold = new SignInGoogleMoneyHold(getActivity(), new SignInGoogleMoneyHold.UpdateSucsess() {
                        @Override
                        public void updateToSucsess() {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                accountName.setText(user.getDisplayName());
                                userEmail.setText(user.getEmail());
                                try {
                                    if (user.getPhotoUrl() != null) {
                                        imagetask.execute(user.getPhotoUrl().toString());
                                    }
                                } catch (Exception o) {

                                }}


                        }

                        @Override
                        public void updateToFailed() {

                        }
                    });
                    if (getContext().getSharedPreferences("infoFirst", getContext().MODE_PRIVATE).getBoolean("FIRSTSYNC", true)) {
                        signInGoogleMoneyHold.openDialog();
                    } else
                        signInGoogleMoneyHold.regitUser();
                }




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
    Bitmap current_bitmap;
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

            File file=new File(getPath(Uri.parse(photoPath)));
            Bitmap bitmap = compressImage(file.getAbsolutePath());

            Log.d("ImageSizeTest", bitmap.getByteCount()+"");

//            Matrix m = new Matrix();
//            m.postRotate(neededRotation(new File(photoPath)));
//            bitmap = Bitmap.createBitmap(bitmap,
//                    0, 0, bitmap.getWidth(), bitmap.getHeight(),
//                    m, true);

            if (bitmap.getWidth() >= bitmap.getHeight()) {
                current_bitmap = Bitmap.createBitmap(
                        bitmap,
                        bitmap.getWidth() / 2 - bitmap.getHeight() / 2,
                        0,
                        bitmap.getHeight(),
                        bitmap.getHeight()
                );
            } else {
                current_bitmap = Bitmap.createBitmap(
                        bitmap,
                        0,
                        bitmap.getHeight() / 2 - bitmap.getWidth() / 2,
                        bitmap.getWidth(),
                        bitmap.getWidth()
                );
            }
            filteredBitmap = current_bitmap;
            PhotoFiltersListAdapter photoFiltersListAdapter = new PhotoFiltersListAdapter(getContext(), Bitmap.createScaledBitmap(current_bitmap, 100, 100, true), new PhotoFiltersListAdapter.AddPhotoEffects() {
                @Override
                public void effectSelected(int positionEffect) {
                    switch (positionEffect){
                        case 0:
                            filteredBitmap = current_bitmap.copy(current_bitmap.getConfig(), true);
                            imageProduct.setImageBitmap( filteredBitmap);
                            break;
                        case 1:
                            filteredBitmap = FiltersCollectionByTojiev.getStarLitFilter().processFilter(current_bitmap.copy(current_bitmap.getConfig(), true));
                            imageProduct.setImageBitmap(filteredBitmap);
                            break;
                        case 2:
                            filteredBitmap = FiltersCollectionByTojiev.getLimeStutterFilter().processFilter(current_bitmap.copy(current_bitmap.getConfig(), true));
                            imageProduct.setImageBitmap(  filteredBitmap);
                            break;
                        case 3:
                            filteredBitmap = FiltersCollectionByTojiev.getNightWhisperFilter().processFilter(current_bitmap.copy(current_bitmap.getConfig(), true));
                            imageProduct.setImageBitmap( filteredBitmap);
                            break;
                        case 4:
                            filteredBitmap = FiltersCollectionByTojiev.getAweStruckVibeFilter().processFilter(current_bitmap.copy(current_bitmap.getConfig(), true));
                            imageProduct.setImageBitmap( filteredBitmap);
                            break;
                        case 5:
                            filteredBitmap = FiltersCollectionByTojiev.getBlueMessFilter().processFilter(current_bitmap.copy(current_bitmap.getConfig(), true));
                            imageProduct.setImageBitmap( filteredBitmap);
                            break;
                        default:
                            filteredBitmap = FiltersCollectionByTojiev.getStarLitFilter().processFilter(current_bitmap.copy(current_bitmap.getConfig(), true));
                            imageProduct.setImageBitmap( filteredBitmap);
                            break;
                    }
                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearManagerWithOutEx(getContext(), LinearLayoutManager.HORIZONTAL, false);
            photoFilters.setLayoutManager(layoutManager);
            photoFilters.setAdapter(photoFiltersListAdapter);
            photoFilters.setVisibility(View.VISIBLE);
            imageProduct.setImageBitmap(current_bitmap);
            rlPriceView.setVisibility(View.VISIBLE);

        }
    }

    private Bitmap decodeFile(File f) {
        try {
//            Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
//            The new size we want to scale to
            final int REQUIRED_SIZE = 80;
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
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }

    public Bitmap compressImage(String imageUri) {

        String filePath = imageUri;
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 600f;
        float maxWidth = 610f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {               imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;             }
            else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight,Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }



        return scaledBitmap;

    }
    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;      }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void savePhotoToCache(String name,Bitmap bmp) throws IOException {
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/LaFloria/photos";
        String file_path_cache = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/LaFloria/cache";

        File dir = new File(file_path);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dir, name + ".jpg");
        FileOutputStream fOut = new FileOutputStream(file);

        bmp.compress(Bitmap.CompressFormat.JPEG,  100, fOut);
        fOut.flush();
        fOut.close();

        File dir2 = new File(file_path_cache);
        if(!dir2.exists())
            dir2.mkdirs();
        File file2 = new File(dir2, name + ".jpg");
        FileOutputStream fOut2 = new FileOutputStream(file2);

        bmp.compress(Bitmap.CompressFormat.JPEG,  30, fOut2);
        fOut2.flush();
        fOut2.close();
    }

}
