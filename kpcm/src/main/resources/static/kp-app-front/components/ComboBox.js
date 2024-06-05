""

import { useState } from 'react';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';

const ComboBox = ({ label, options, defaultValue, onChange }) => {
  const [value, setValue] = useState(defaultValue);

  const handleChange = (event) => {
    const newValue = event.target.value;
    setValue(newValue);
    if (onChange) {
      onChange(newValue);
    }
  };

  return (
    <TextField
      select
      label={label}
      value={value}
      onChange={handleChange}
      fullWidth
    >
      {options.map((option) => (
        <MenuItem key={option.value} value={option.value}>
          {option.label}
        </MenuItem>
      ))}
    </TextField>
  );
};

export default ComboBox;


// import React from 'react';
// import ComboBox from './components/ComboBox'; // 경로는 실제 프로젝트의 구조에 따라 조정

// function ExampleComponent() {
//   const options = [
//     { value: 'option1', label: '옵션 1' },
//     { value: 'option2', label: '옵션 2' },
//     { value: 'option3', label: '옵션 3' }
//   ];

//   const handleComboBoxChange = (selectedValue) => {
//     console.log('선택된 값:', selectedValue);
//   };

//   return (
//     <div>
//       <ComboBox
//         label="선택하세요"
//         options={options}
//         defaultValue="option1"
//         onChange={handleComboBoxChange}
//       />
//     </div>
//   );
// }

// export default ExampleComponent;