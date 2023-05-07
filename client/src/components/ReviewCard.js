import React from 'react';
import styled from 'styled-components';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import Rating from '@mui/material/Rating';

const ReviewCard = () => {
  const data = {
    name: '김희진',
    content:
      '이력서 작성이 막막했는데 로켓CV로 10분만에 이력서를 완성했어요! 취업 뽀개자!',

    name2: '김도현',
    content2:
      '헤에-? 이력서가 이렇게 한번에 만들어진다구요? 어디든 취업할 수 있겠네요! 고마워요 로켓CV!',

    name3: '강동욱',
    content3:
      '로켓CV로 작성한 이력서를 통해 카카오에 입사할뻔 했습니다. 추천합니다',
  };
  return (
    <>
      <Econtainer>
        <AccountCircleIcon />
        <span>{data.name}</span>
        <Rating name="read-only" value="5" readOnly />
        <p>{data.content}</p>
      </Econtainer>

      <Econtainer>
        <AccountCircleIcon />
        <span>{data.name2}</span>
        <Rating name="read-only" value="5" readOnly />
        <p>{data.content2}</p>
      </Econtainer>

      <Econtainer>
        <AccountCircleIcon />
        <span>{data.name3}</span>
        <Rating name="read-only" value="5" readOnly />
        <p>{data.content3}</p>
      </Econtainer>
    </>
  );
};

export default ReviewCard;

const Econtainer = styled.div`
  display: flex;
  width: 12rem;
  background-color: white;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  height: 14rem;
  text-align: center;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  margin: 0rem 2rem 10rem 2rem;
  span {
    font-size: 1.2rem;
  }
  p {
    margin: 1rem;
    text-align: left;
    font-size: 0.8rem;
    color: gray;
  }
`;
