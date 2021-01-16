package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter1 extends FragmentStateAdapter {
    ClickInterface1 listener;

    public FragmentAdapter1(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position) {
            // case 0:return new calc_fragment();
            //default:return new vista_fragment();
            case 0:
                return new carrito_fragment();
            case 1:
                return new catalogo();
            default:
                return new ingresodatfr();


        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
