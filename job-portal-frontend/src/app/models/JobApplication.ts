import { JobPost } from "./JobPost";

export interface JobApplication {
  applicaitonId?: number;
  name: string;
  email: string;
  coverLetter?: string | null;
  resumeFileName?: string;
  resumeFilePath: string;
  appliedAt?: Date;
  jobId: number;
  jobProfile: string;
}
