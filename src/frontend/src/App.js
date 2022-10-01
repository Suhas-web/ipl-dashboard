import "./App.css";
import { TeamPage } from "./pages/TeamPage.js";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { MatchPage } from "./pages/MatchPage";
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/teams/:teamName" element={<TeamPage />}></Route>
        <Route
          path="/teams/:teamName/matches/:year"
          element={<MatchPage />}
        ></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
