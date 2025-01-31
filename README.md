# LeetcodeForge

A platform for practicing coding problems (like LeetCode) with integrated code execution and problem management.

![LeetcodeForge Screenshot](https://your-screenshot-url-here.com) <!-- Add screenshots later -->

## Features

- **Problem Management**: Create, edit, and organize coding problems with test cases.
- **Code Execution**: Write and test code directly in the browser with a built-in editor.
- **User Authentication**: Sign up, log in, and track progress.
- **Dark Mode**: Supports light/dark themes.
- **Real-time Feedback**: Validate solutions against test cases.

---

## Tech Stack

### Frontend

- **Framework**: Next.js (React)
- **UI**: Radix UI, Tailwind CSS, Shadcn/ui
- **State Management**: React Query
- **Code Editor**: Monaco Editor (via `@monaco-editor/react`)
- **Form Handling**: React Hook Form + Zod
- **Notifications**: Sonner (toasts)
- **Animations**: Tailwind CSS Animate

### Backend

- **Framework**: Node.js + Express
- **Database**: MongoDB (with Mongoose)
- **Authentication**: JWT
- **Validation**: Zod
- **Logging**: Morgan

---

## Project Structure

```bash
LeetcodeForge/
├── backend/           # Backend code (Express.js + MongoDB)
│   ├── models/       # MongoDB schemas
│   ├── routes/       # API routes (auth, problems, etc.)
│   ├── middleware/   # Authentication middleware
│   └── server.js     # Entry point
│
├── frontend/         # Next.js application
│   ├── app/          # Next.js app router
│   ├── components/   # Reusable UI components (CodeEditor, ProblemDescription, etc.)
│   ├── lib/          # Utilities (API clients, auth logic)
│   └── ...
│
└── README.md         # Project documentation
```
