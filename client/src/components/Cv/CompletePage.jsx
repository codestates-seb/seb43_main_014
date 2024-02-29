import React from 'react';
import styled from 'styled-components';
import Logo from '../../images/rocket.png';
import AssignmentTurnedInIcon from '@mui/icons-material/AssignmentTurnedIn';
import { useEffect } from 'react';

const CompletePage = ({ setCheck }) => {
  useEffect(() => {
    setCheck(true);
  }, []);
  console.log(3, setCheck);
  return (
    <Container>
      <img className="Logo" src={Logo} alt="로고 이미지" />
      <div className="styledtext">
        <h1>로켓처럼 빠른 이력서 작성 서비스 로켓CV</h1>
      </div>
      <span>이력서 작성을 완료하셨나요?</span>
      <span>수정할 사항이 있다면 `이전` 버튼을,</span>
      <span>작성을 완료하셨다면 `완료` 버튼으로 이력서를 저장하세요!</span>
      <div className="icon">
        <AssignmentTurnedInIcon />

        <span>
          로켓 CV를 통해 이력서를 추가 작성하고, 마이페이지에서 이력서를
          관리해보세요!
        </span>
      </div>

      <div className="agree">
        <span>
          완료 버튼을 클릭하여 다음 단계를 진행하면, 귀하는 개인 정보 보호
          정책에 동의하게 됩니다.
        </span>
      </div>
    </Container>
  );
};

export default CompletePage;

const Container = styled.div`
  margin: 0 4rem 8rem 4rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  width: 55rem;
  background-color: white;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  .styledtext {
    margin: 1rem 0rem 2rem 0rem;
  }
  .Logo {
    margin-top: 2rem;
    width: 20rem;
  }
  span {
    font-size: 1.3rem;
  }
  .agree {
    margin: 2rem 0rem 2rem 0rem;
    span {
      font-size: 0.6rem;
      color: gray;
    }
  }
  .icon {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 5rem 0rem 8rem 0rem;
    height: 10rem;
    width: 45rem;

    background-color: var(--puple300);
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  }
`;
