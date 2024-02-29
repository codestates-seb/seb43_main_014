export const validate = (form) => {
  const newErrors = {
    username: '',
    password: '',
  };

  const newValid = {
    username: false,
    password: false,
  };

  if (form.username.trim() === '') {
    newErrors.username = '이메일을 입력해주세요.';
  } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(form.username)) {
    newErrors.username = '올바른 이메일 형식이 아닙니다.';
  } else {
    newErrors.username = '올바른 형식입니다.';
    newValid.username = true;
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

  return [newErrors, newValid];
};
