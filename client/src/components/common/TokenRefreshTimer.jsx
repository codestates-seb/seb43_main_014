import { useEffect, memo } from 'react';
import { useRecoilState } from 'recoil';
import {
  isLoginState,
  tokenState,
  refreshTokenState,
} from '../../recoil/AuthAtom';
import { localStorageGet } from '../../utils/localstorageFunc';
import axios from 'axios';

const TokenRefreshTimer = memo(() => {
  // 액세스 토큰 재발급 함수

  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [token, setToken] = useRecoilState(tokenState);
  const [refreshToken, setRefreshToken] = useRecoilState(refreshTokenState);

  const reissueAccessToken = () => {
    const [access_token, refresh_token, ,] = localStorageGet();
    console.log('액세스 토큰 재발급');
    axios
      .post(
        'http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user/reissue',
        {
          accessToken: access_token,
          refreshToken: refresh_token,
        },
      )
      .then((res) => {
        console.log(res);

        localStorage.setItem('refresh_token', res.data.data.refreshToken);
        localStorage.setItem('jwt_token', res.data.data.accessToken);

        setToken(res.data.data.accessToken);
        setRefreshToken(res.data.data.refreshToken);
      });
  };

  useEffect(() => {
    const [, , , is_Login] = localStorageGet();
    if (is_Login) {
      // 로그인 요청 성공 후 로그인 상태로 설정되었을 때만 타이머 시작
      const intervalId = setInterval(reissueAccessToken, 5000); // 29분 마다 액세스 토큰 재발급
      console.log('액세스 토큰 재발급');

      // 컴포넌트가 언마운트될 때 clearInterval을 사용하여 interval 정리
      return () => {
        if (intervalId) {
          console.log('인터벌 타이머 정리');
          clearInterval(intervalId);
        }
      };
    }
  }, [isLogin]);

  return null;
});

TokenRefreshTimer.displayName = 'TokenRefreshTimer';

export default TokenRefreshTimer;
