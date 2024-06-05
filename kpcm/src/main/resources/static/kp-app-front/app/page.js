"use client";
import { useState,useRef } from 'react';
import { useRouter } from 'next/navigation';
import { Container, TextField, Button, Typography, Grid, Snackbar } from '@mui/material';
/**
 * @description: 로그인을 위한 화면.
 * @function cm0101mq0
 * @param {*} props
 * @returns
 * 변경이 있을 때에는 수정 이ㅣ력에 변경일자와 변경자, 그리고 변경사유를 기록하여 관리가 되도록 한다.
 * ========================================================================================================
 *                                    수정 이력관리 (형상관리에도 Copy휴 반영)
 * --------------------------------------------------------------------------------------------------------
 *      수정일        수정자                                  수정내용
 * --------------------------------------------------------------------------------------------------------
 *   2024.05.15       정성현                                  최초작성
 *   2024.05.16       홍길동                     Method 수정및 추가작업
 * ========================================================================================================
 */
const LoginPage = () => {
  const router = useRouter();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [openAlert, setOpenAlert] = useState(false);
  const [alertMessage, setAlertMessage] = useState('이메일과 비밀번호를 입력해주세요.');
  const emailRef = useRef(null); // 참조 생성
  async function handleLogin(){
    // 이메일과 비밀번호가 입력되었는지 확인
    if (!email || !password) {
        setAlertMessage("이메일과 비밀번호를 입력해주세요.");
        setOpenAlert(true);
        return;
    }
    const res = await	fetch(`${process.env.NEXT_PUBLIC_API_URL}/cm0101/selectUser`,{
        cache:'no-store',
        method : 'POST',
        headers : {
          'Content-Type' : 'application/json',
        },
        body : JSON.stringify({mbrEmlAddr:email, userPswd:password})
      })
      .then((response) => {
        if(response.status === 200){
          return response.json();
        }else {
          // 서버 에러 코드 전송 시 실행할 부분
        }
      })
      .then((result) => {
        // 성공 시 실행할 부분
        return result;
      })
      .catch((err) => {
        // 인터넷 문제로 실패 시 실행할 부분
        console.log(err);
      });
    //   if (!res.ok) {
    //     throw new Error('Network response was not ok');
    //   }      
      if (!res.Alllist || res.Alllist.length === 0) {
        setAlertMessage("사용자정보가 잘못 되었습니다.");
        setOpenAlert(true);
        emailRef.current.focus(); // 포커스 이동
        return;
    }

    router.push('/main');
  };

  const handleCloseAlert = () => {
    setOpenAlert(false);
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
      <Container maxWidth="sm">
        <Grid container spacing={2} justifyContent="center" alignItems="center">
          <Grid item xs={12}>
            <Typography variant="h4" align="center" gutterBottom>
              한국조폐공사 결제플랫폼 로그인
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <TextField
              fullWidth
              label="이메일"
              variant="outlined"
              type="email"
              autoFocus
              margin="normal"
              value={email}
              inputRef={emailRef}
              onChange={(e) => setEmail(e.target.value)}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              fullWidth
              label="비밀번호"
              variant="outlined"
              type="password"
              margin="normal"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </Grid>
          <Grid item xs={12}>
            <Button
              fullWidth
              variant="contained"
              color="primary"
              size="large"
              onClick={handleLogin}
            >
              로그인
            </Button>
          </Grid>
        </Grid>
        <Snackbar
          open={openAlert}
          autoHideDuration={6000}
          onClose={handleCloseAlert}
          message={alertMessage}
          anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'center',
          }}
        />
      </Container>
    </div>
  );
}

export default LoginPage;