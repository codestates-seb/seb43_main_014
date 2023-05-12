import React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { useState } from 'react';
import styled from 'styled-components';
import CvSkillInput from './CvCustomInput';
import Box from '@mui/material/Box';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import FavoriteIcon from '@mui/icons-material/Favorite';
import NavigationIcon from '@mui/icons-material/Navigation';

const days = [
  '일',
  '1',
  '2',
  '3',
  '4',
  '5',
  '6',
  '7',
  '8',
  '9',
  '10',
  '11',
  '12',
  '13',
  '14',
  '15',
  '16',
  '17',
  '18',
  '19',
  '20',
  '21',
  '22',
  '23',
  '24',
  '25',
  '26',
  '27',
  '28',
  '29',
  '30',
  '31',
];

const months = [
  '월',
  '1',
  '2',
  '3',
  '4',
  '5',
  '6',
  '7',
  '8',
  '9',
  '10',
  '11',
  '12',
];
const years = [
  '년',
  '2012',
  '2011',
  '2010',
  '2009',
  '2008',
  '2007',
  '2006',
  '2005',
  '2004',
  '2003',
  '2002',
  '2001',
  '2000',
  '1999',
  '1998',
  '1997',
  '1996',
  '1995',
  '1994',
  '1993',
  '1992',
  '1991',
  '1990',
  '1989',
  '1988',
  '1987',
  '1986',
  '1985',
  '1984',
  '1983',
  '1982',
  '1981',
  '1980',
  '1979',
  '1978',
  '1977',
  '1976',
  '1975',
  '1974',
  '1973',
  '1972',
  '1971',
  '1970',
  '1969',
  '1968',
  '1967',
  '1966',
  '1965',
  '1964',
  '1963',
  '1962',
  '1961',
  '1960',
];
const jobs = [
  '직무',
  '서버/백엔드 개발자',
  '프론트엔드 개발자',
  '웹 풀스택 개발자',
  '안드로이드 개발자',
  'IOS 개발자',
  '블록체인',
  '개발 PM',
];
const CvBasicInfo = () => {
  const [month, setMonth] = useState('');
  const [day, setDay] = useState('');
  const [year, setYear] = useState('');
  const [job, setJob] = useState('');

  const onChange = (event) => {
    const {
      target: { name, value },
    } = event;
    if (name === 'day') {
      setDay(value);
    } else if (name === 'month') {
      setMonth(value);
    } else if (name === 'year') {
      setYear(value);
    } else if (name === 'job') {
      setJob(value);
    }
    console.log(value);
  };

  return (
    <Container>
      <div className="body">
        <div className="title">
          <input placeholder="이력서 제목"></input>
          <hr></hr>
        </div>
        <div className="test2">
          <div className="photo">
            <img src="https://blog.kakaocdn.net/dn/OZ3vp/btqWW9GQeUf/AscsDSgZbtKRKXxMuw2bPk/img.jpg" />
          </div>
          <div className="name">
            <span>성명</span>
            <input></input>
            <span>이메일 주소</span>
            <input></input>
            <span>전화번호</span>
            <input></input>
            <span>주소</span>
            <input></input>
          </div>
        </div>
        <div className="test2">
          <div className="birth2">
            <span>생년월일</span>
            <FormControl sx={{ m: 1, width: 115, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="month"
                value={month}
                label="Month"
                onChange={onChange}
              >
                {months.map((month) => (
                  <MenuItem key={month} value={month}>
                    {month}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
            <FormControl sx={{ m: 1, width: 120 }}>
              <InputLabel id="demo-simple-select-label">일</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="day"
                value={day}
                label="Day"
                onChange={onChange}
              >
                {days.map((day) => (
                  <MenuItem key={day} value={day}>
                    {day}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
            <FormControl sx={{ m: 1, width: 170 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="year"
                value={year}
                label="Year"
                onChange={onChange}
              >
                {years.map((year) => (
                  <MenuItem key={year} value={year}>
                    {year}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </div>
          <div className="develop">
            <span>개발 직무</span>

            <FormControl sx={{ m: 1, width: 190 }}>
              <InputLabel id="demo-simple-select-label">직무</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="job"
                value={job}
                label="job"
                onChange={onChange}
              >
                {jobs.map((job) => (
                  <MenuItem key={job} value={job}>
                    {job}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </div>
        </div>

        <div className="intro">
          <span>자기소개</span>
          <input></input>
        </div>

        <div className="tag">
          <span>기술스택 태그</span>
          <Box sx={{ '& > :not(style)': { m: 1 } }}>
            <Fab color="primary" variant="extended">
              React
            </Fab>
            <Fab color="secondary" variant="extended">
              Javascript
            </Fab>
            <Fab variant="extended">Java</Fab>
          </Box>
        </div>
        <div className="link">
          <div>
            <span>링크</span>
          </div>
          <div>
            <input></input>
            <input></input>
            <input></input>
          </div>
        </div>
        <div className="port">
          <span>포트폴리오</span>
          <input></input>
        </div>
      </div>
    </Container>
  );
};

export default CvBasicInfo;

const Container = styled.div`
  margin: 0rem 4rem 8rem 4rem;
  display: flex;
  flex-direction: column;

  width: 55rem;
  background-color: white;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  input {
    width: 32rem;
    height: 2.5rem;
    margin: 0 0 1rem 0rem;
    border-radius: 0.2rem;
    border: 1px solid #c8c8c8;
    :hover {
      border: 1px solid black;
    }
  }
  span {
    font-size: 1rem;
    font-weight: bold;
    color: gray;
  }

  img {
    width: 16rem;
  }

  .name {
    display: flex;
    flex-direction: column;
    width: 41rem;
  }
  .test2 {
    display: flex;
  }
  .photo {
    width: 20rem;
    height: 20rem;
    margin: 0 1rem 0 0rem;
  }
  .body {
    margin: 3rem;
  }
  .birth2 {
    width: 32.2rem;
  }
  .develop {
    width: 16.8rem;
  }

  .intro {
    margin: 1rem 0 0 0;
    input {
      width: 100%;
      height: 20rem;
    }
  }
  .port {
    width: 20rem;
  }
  .title {
    input {
      margin: 0;
    }
  }
`;
