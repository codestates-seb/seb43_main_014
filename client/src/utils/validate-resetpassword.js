export const validate = (form) => {
  const newErrors = {
    email: '',
  };
  const newValid = {
    email: false,
  };

  if (form.email.trim() === '') {
    newErrors.email = '이메일을 입력해주세요.';
  } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(form.email)) {
    newErrors.email = '올바른 이메일 형식이 아닙니다.';
  } else {
    newErrors.email = '올바른 형식입니다.';
    newValid.email = true;
  }

  return [newErrors, newValid];
};
