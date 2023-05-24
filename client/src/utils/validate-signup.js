export const validate = (form) => {
  const newErrors = {
    name: '',
    phone: '',
    email: '',
    password: '',
    password_confirm: '',
  };
  const newValid = {
    name: false,
    phone: false,
    email: false,
    password: false,
    password_confirm: false,
  };

  if (!/^[\uac00-\ud7a3]+$/.test(form.name)) {
    newErrors.name = '이름은 한글만 입력 가능합니다.';
  } else if (!/^(?=.{2,20}$)[가-힣]{2,20}$/.test(form.name)) {
    newErrors.name = '이름은 2~20자 사이의 한글만 입력 가능합니다.';
  } else {
    newErrors.name = '올바른 형식입니다.';
    newValid.name = true;
  }

  if (form.phone.trim() === '') {
    newErrors.phone = '휴대폰 번호를 입력해주세요.';
  } else if (!/^010-\d{4}-\d{4}$/.test(form.phone)) {
    newErrors.phone =
      "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.";
  } else {
    newErrors.phone = '올바른 형식입니다.';
    newValid.phone = true;
  }

  if (form.email.trim() === '') {
    newErrors.email = '이메일을 입력해주세요.';
  } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(form.email)) {
    newErrors.email = '올바른 이메일 형식이 아닙니다.';
  } else {
    newErrors.email = '올바른 형식입니다.';
    newValid.email = true;
  }

  if (form.password.trim() === '') {
    newErrors.password = '비밀번호를 입력해주세요.';
  } else if (form.password.length < 8) {
    newErrors.password = '비밀번호는 8자리 이상이어야 합니다.';
  } else if (
    !/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$/.test(form.password)
  ) {
    newErrors.password =
      '영문자, 숫자, 특수문자를 포함한 8글자 이상의 비밀번호를 입력해주세요.';
  } else {
    newErrors.password = '올바른 형식입니다.';
    newValid.password = true;
  }

  if (form.password_confirm.trim() === '') {
    newErrors.password_confirm = '비밀번호를 다시 입력해주세요.';
  } else if (form.password !== form.password_confirm) {
    newErrors.password_confirm = '비밀번호가 일치하지 않습니다.';
  } else if (form.password === form.password_confirm) {
    newErrors.password_confirm = '비밀번호가 일치합니다.';
    newValid.password_confirm = true;
  }
  return [newErrors, newValid];
};
