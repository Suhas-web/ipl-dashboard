import "./App.css";
import { TeamPage } from "./pages/TeamPage.js";
import { BrowserRouter, Route, Routes } from "react-router-dom";
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/teams/:teamName" element={<TeamPage />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
