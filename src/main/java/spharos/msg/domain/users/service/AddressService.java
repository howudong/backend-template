package spharos.msg.domain.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spharos.msg.domain.users.dto.in.AddressRequestDto;
import spharos.msg.domain.users.dto.out.AddressOutDto;
import spharos.msg.domain.users.entity.Address;
import spharos.msg.domain.users.repository.AddressRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressOutDto createAddress(AddressRequestDto addressRequestDto) {
        Address address = addressRepository.save(Address
                .builder()
                .mobileNumber(addressRequestDto.getMobileNumber())
                .addressName(addressRequestDto.getAddressName())
                .addressPhoneNumber(addressRequestDto.getAddressPhoneNumber())
                .recipient(addressRequestDto.getRecipient())
                .users(addressRequestDto.getUsers())
                .address(addressRequestDto.getAddress())
                .build());

        //todo : 반환값 확인 필요
        return null;
    }
}