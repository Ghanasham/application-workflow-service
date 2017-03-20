# application-workflow-service
Generic application workflow service which can be used to create different types of application workflows like home loan application workflow

Rest Endpoints

Applicant

POST /register/applicant

GET /register/applicant/{applicantId}

PUT /register/applicant/{applicantId}

DELET /register/applicant/{applicantId}


HomeLoan Application

GET /home-loan/application/{applicationId}

POST /home-loan/application

PUT /home-loan/application/{applicationId}

DELETE /home-loan/application/{applicationId}


Manager

GET /manage/application/{applicationId}

PUT /manage/application/{applicationId}
