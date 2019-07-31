package com.luisrosales.notes;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.luisrosales.notes.db.entity.NotaEntity;

public class NuevaNotaDialogFragment extends DialogFragment  {

    public static NuevaNotaDialogFragment newInstance() {

        return new NuevaNotaDialogFragment();
    }
    private View view;

    private EditText editTextTitulo, editTextContenido;
    private RadioGroup radioGroupColor;
    private Switch aSwitchFavorita;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nueva nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo = editTextTitulo.getText().toString();
                        String contenido = editTextContenido.getText().toString();
                        String color = "Azul";
                        switch (radioGroupColor.getCheckedRadioButtonId()){
                            case R.id.radioButtonColorRojo:
                                color = "Rojo";
                            case R.id.radioButtonColorVerde:
                                color = "Verde";
                        }
                        boolean esFavorita = aSwitchFavorita.isChecked();

                        NuevaNotaDialogViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NuevaNotaDialogViewModel.class);

                        //Comunicar al viwemodel que hay una nueva nota
                        mViewModel.insertarNota(new NotaEntity(titulo,contenido,esFavorita,color));
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.nueva_nota_dialog_fragment,null);// null porque no es devuelto a otro layout raiz

        editTextTitulo = view.findViewById(R.id.editTextTitulo);
        editTextContenido = view.findViewById(R.id.editTextContenido);
        radioGroupColor = view.findViewById(R.id.radioGroupColor);
        aSwitchFavorita = view.findViewById(R.id.switchNotaFavorita);

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
