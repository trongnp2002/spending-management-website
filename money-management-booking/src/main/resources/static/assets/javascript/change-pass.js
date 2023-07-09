function report(error){
    if(error != null && error != ''){
        if(error == 'wrong-password'){
            alert("Old password not correct!!");
        }
        if(error == 'not-match' ){
            alert("New password and re-password not match");
        }
        if(error =='success'){
            alert('Update password successfull!!!');
        }
        if(error == 'cannot-be-empty'){
            alert('Data cannot be empty!!')
        }
    }
}