<template>
    <div>
      <input type="file" ref="fileInput" @change="uploadFile" />
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    methods: {
      uploadFile() {
        const fileInput = this.$refs.fileInput;
        const file = fileInput.files[0];
        const formData = new FormData();
        formData.append('file', file);
  
        axios.post('http://localhost:8080/api/upload', formData)
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.error('Error uploading file:', error);
          });
      },
    },
  };
  </script>