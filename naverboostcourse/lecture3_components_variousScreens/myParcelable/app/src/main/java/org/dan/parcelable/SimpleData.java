package org.dan.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

//
// Parcel은 객체안의 데이터를 다른데 전달할때 담아서 쓰이는 컨테이너 같은 녀석 -> 단어 뜻을 생각하면 이해 쉽다.
//

public class SimpleData implements Parcelable {

    Integer number;
    String message;

    public SimpleData(Integer number, String message) {
        this.number = number;
        this.message = message;
    }

    //Parcel에서 SimpleData안에 들어가 있는 변수를 read()로 복원
    public SimpleData(Parcel src){
        number = src.readInt();
        message = src.readString();
    }

    public static final Creator CREATOR = new Parcelable.Creator() {

        public SimpleData createFromParcel(Parcel src){
            return new SimpleData(src); //생성자 호출에서 변수 초기화
        }
        public SimpleData[] newArray(int size){
            return new SimpleData[size];
        }
    };


    //Parcelable interface methods//

    @Override
    public int describeContents() {
        return 0;
    }

    //SimpleData -> Parcel 타입으로 변환
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeInt(number);
        parcel.writeString(message);

    }
}
