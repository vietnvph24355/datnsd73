package com.example.fploy.datn.entity.trangThai;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StatusSerializer extends JsonSerializer<TrangThaiVoucher> {
    @Override
    public void serialize(TrangThaiVoucher trangThaiVoucher, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(trangThaiVoucher.toString());
    }
}
