export const validate = (form) => {
  const newErrors = {
    phone: '',
  };
  const newValid = {
    phone: false,
  };

  if (form.phone.trim() === '') {
    newErrors.phone = '휴대폰 번호를 입력해주세요.';
  } else if (!/^010-\d{4}-\d{4}$/.test(form.phone)) {
    newErrors.phone =
      "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.";
  } else {
    newErrors.phone = '올바른 형식입니다.';
    newValid.phone = true;
  }

  return [newErrors, newValid];
};
