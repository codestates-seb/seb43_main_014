// 로컬 스토리지에 JSON 형태로 저장 / 불러오기 / 삭제하기 모듈
const storage = {
  set: (key, object) => {
    if (!localStorage) return;
    localStorage[key] = typeof object === 'string' ? object : JSON.stringify(object);
  },
  get: (key) => {
    if (!localStorage) return null;

    if (!localStorage[key]) {
      return null;
    }

    try {
      const parsed = JSON.parse(localStorage[key]);
      return parsed;
    } catch (e) {
      return localStorage[key];
    }
  },
  remove: (key) => {
    if (!localStorage) return null;

    if (localStorage[key]) {
      localStorage.removeItem(key);
    }
  },
  clear: () => {
    if (!localStorage) return null;

    localStorage.clear();
  },
};

export default storage;

// 사용법 :
// storage.set('foo', 'bar');
// storage.set('foobar', { foo: 'bar' });
// let foo = storage.get('foo'); // bar
// storage.remove('foo');

// 파라미터가 객체형태로 들어오면 자동으로 JSON.stringify 를 해주고, 일반 문자열이라면 그대로 넣어줌
// 조회를 할 때도 객체형태라면 자동으로 파싱해줌
