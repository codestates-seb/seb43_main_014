import React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { useState } from 'react';
import styled from 'styled-components';
import CvSkillInput from './CvSkillInput';

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
const CvCustomInput = () => {
  const [month, setMonth] = useState('');
  const [day, setDay] = useState('');
  const [year, setYear] = useState('');
  const [job, setJob] = useState('');

  const handleChange = (event) => {
    setMonth(event.target.value);
  };
  const handleChange2 = (event) => {
    setDay(event.target.value);
  };
  const handleChange3 = (event) => {
    setYear(event.target.value);
  };
  const handleChange4 = (event) => {
    setJob(event.target.value);
  };
  return (
    <InputContainer>
      <div>
        <input placeholder="이력서 제목"></input>
        <input type="file" accept="image/*"></input>
        <h1>성명</h1>
        <input></input>
        <h1>이메일 주소</h1>
        <input></input>
        <h1>전화번호</h1>
        <input></input>
        <h1>주소</h1>
        <input></input>
        <h1>생년월일</h1>
      </div>
      <FormControl sx={{ m: 1, width: 100 }}>
        <InputLabel id="demo-simple-select-label">월</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          name="month"
          value={month}
          label="Month"
          onChange={handleChange}
        >
          {months.map((month) => (
            <MenuItem key={month} value={month}>
              {month}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <FormControl sx={{ m: 1, width: 100 }}>
        <InputLabel id="demo-simple-select-label">일</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          name="day"
          value={day}
          label="Day"
          onChange={handleChange2}
        >
          {days.map((day) => (
            <MenuItem key={day} value={day}>
              {day}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <FormControl sx={{ m: 1, width: 120 }}>
        <InputLabel id="demo-simple-select-label">년</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          name="year"
          value={year}
          label="Year"
          onChange={handleChange3}
        >
          {years.map((year) => (
            <MenuItem key={year} value={year}>
              {year}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <h1>자기소개</h1>
      <input></input>
      <h1>개발 직무</h1>
      <FormControl sx={{ m: 1, width: 200 }}>
        <InputLabel id="demo-simple-select-label">직무</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          name="job"
          value={job}
          label="job"
          onChange={handleChange4}
        >
          {jobs.map((job) => (
            <MenuItem key={job} value={job}>
              {job}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <h1>기술스택 태그</h1>
      <h1>링크</h1>
      <input></input>
      <input></input>
      <input></input>
      <h1>첨부파일</h1>
      <input type="file" accept="pdf/*"></input>
      <CvSkillInput />
    </InputContainer>
  );
};

export default CvCustomInput;

const InputContainer = styled.div`
  margin: 4rem 0 4rem 0;
  width: 100%;
  display: flex;
  flex-direction: column;
  background-color: white;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
`;
